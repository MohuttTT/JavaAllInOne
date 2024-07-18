package org.zerock.b01.repository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.BoardImage;
import org.zerock.b01.dto.BoardListAllDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("title..." + i)
                    .content("content..." + i)
                    .writer("user" + (i % 10))
                    .build();

            Board result = boardRepository.save(board);
            log.info("BNO : {}", result.getBno());
        });
    }

    @Test
    public void testSelect() {
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info(board);
    }

    @Test
    public void testUpdate() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();

        board.change("update..title 100", "update...content 100");

        boardRepository.save(board);
    }

    @Test
    public void testDelect() {
        Long bno = 1L;
        boardRepository.deleteById(bno);
    }

    @Test
    public void testPaging() {

        // Pageable Interface : 페이징 처리와 관련된 정보를 캡슐화하고 있는 인터페이스 (페이지 번호, 페이지 크기, 정렬 순서 등의 정보 포함)
        // PageRequest : Pageable 인터페이스를 구현한 구현체
        /*
            정적 메서드 of를 통해 쉽게 인스턴스 생성 가능
            받고 있는 매개변수 (int page(요청할 페이지 번호), int size(페이지당 항목 수), [Sort sort(정렬 기준)])
         */

        // 1 page order by bno desc => 첫 번째 페이지(0)에서 10개의 항목을 bno 필드를 기준으로 desending(내림차순) 정렬하여 가져오도록 객체 생성
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        // Page<T> Interface : 페이징 처리된 결과를 나타내는 인터페이스 (페이징된 데이터와 관련된 메타데이터(전체 페이지 수, 현재 페이지 번호 등)을 포함
        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count : {}", result.getTotalElements()); // 전체 데이터 개수 반환 (데이터베이스에 저장된 총 Board 엔티티의 개수)
        log.info("total pages : {}", result.getTotalPages()); // 전체 페이지 수
        log.info("page number : {}", result.getNumber()); // 현재 페이지 번호 -- 페이지 번호는 0번부터 시작
        log.info("page size : {}", result.getSize()); // 현재 페이지에서 조회하고 있는 데이터 수(페이지 크기)

        List<Board> todoList = result.getContent(); // 현재 페이지에 있는 Board 엔티티 리스트를 반환 (페이징된 실제 데이터)

        todoList.forEach(log::info);

    }

    @Test
    public void testSearch1() {
        // 2 page order by bno desc
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());

        boardRepository.search1(pageable);
    }


    @Test
    public void testSearchAll() {
        String[] types = {"t", "c", "w"}; // -- 검색 조건
        String keyword = "1"; // 검색 키워드
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending()); // -- 페이징 (첫 페이지(0)에 10개 항목을 bno 필드 기준으로 내림차순 정렬
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
    }

    @Test
    public void testSearchAll2() {
        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);


        // [페이징 정보 로그 출력]
        log.info(result.getTotalPages()); // -- total pages

        log.info(result.getSize()); // -- page size (한 페이지에 표시되는 항목 수)

        log.info(result.getNumber()); // -- pageNumber (현재 페이지 번호)

        log.info("{}: {}", result.hasPrevious(), result.hasNext()); // -- prev next 존재 여부

        result.getContent().forEach(log::info); // -- 검색 결과 목록을 각각 출력
    }

    @Test
    public void testSearchReplyCount() {
        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);


        // [페이징 정보 로그 출력]
        log.info(result.getTotalPages()); // -- total pages

        log.info(result.getSize()); // -- page size (한 페이지에 표시되는 항목 수)

        log.info(result.getNumber()); // -- pageNumber (현재 페이지 번호)

        log.info("{}: {}", result.hasPrevious(), result.hasNext()); // -- prev next 존재 여부

        result.getContent().forEach(log::info); // -- 검색 결과 목록을 각각 출력
    }

    @Test
    public void testInsertWithImages() {

        Board board = Board.builder()
                .title("Image Test")
                .content("첨부파일 테스트")
                .writer("tester")
                .build();

        for (int i = 0; i < 3; i++) {

            board.addImage(UUID.randomUUID().toString(), "file" + i + ".jpg");
        }   // end for

        boardRepository.save(board);
    }

    // 게시글 조회(이미지 포함 부분)
    @Test
    public void testReadWithImages() {

        // 반드시 존재하는 bno로 확인
        // 1. Optional<Board> result = boardRepository.findById(1L);

        // 2. 해결 -- 해당 번호의 게시글 select시, 관련된 이미지 파일도 함께 조회할 수 있도록 join문 실행
        Optional<Board> result = boardRepository.findByIdWithImages(1L);

        Board board = result.orElseThrow();

        log.info(board);
        log.info("----------------------");
        // 1. log.info(board.getImageSet()); -- 오류 발생
        // 2. 해결 -- select 진행 시 board 테이블과
        for (BoardImage boardImage : board.getImageSet()) {
            log.info(boardImage);
        }
    }

    @Transactional
    @Commit
    @Test
    public void testModifyImages() {

        // 1. 문제 -- cascade 속성이 ALL로 지정되었기 때문에 상위 엔티티(Board) 상태 변화가 하위 엔티티까지 영향을 주지만, 삭제는 되지 X
        // -- 수정해야 하는 부분 : 하위 엔티티의 참조 값이 더 이상 없는 상태가 되면 실제로 삭제가 이루어져야 함
        // -- @OneToMany에 orphanRemoval 속성을 이용하여 설정
        Optional<Board> result = boardRepository.findByIdWithImages(1L);

        Board board = result.orElseThrow();

        // 기존의 첨부파일들은 삭제
        board.clearImages();

        // 새로운 첨부파일들
        for (int i = 0; i < 2; i++) {

            board.addImage(UUID.randomUUID().toString(), "updatefile" + i + ".jpg");
        }

        boardRepository.save(board);
    }

    @Test
    @Transactional
    @Commit
    public void testRemoveAll() {

        Long bno = 1L;

        replyRepository.deleteByBoard_Bno(bno); // 해당 게시글의 댓글 삭제

        boardRepository.deleteById(bno);        // 해당 게시물 삭제
        /*
            CascadeType.ALL 속성으로 관련된 하위 엔티티(해당 게시글의 첨부파일)도 모두 삭제된다.
        */
    }

    @Test
    // 번호가 5, 10, 15..의 경우에 첨부파일이 없는 게시물이 작성되고, 나머지는 3개의 첨부파일이 있는 상태로 구성
    public void testInsertAll() {

        for (int i = 1; i <= 100; i++) {

            Board board = Board.builder()
                    .title("Title.." + i)
                    .content("Content.." + i)
                    .writer("writer.." + i)
                    .build();

            for (int j = 0; j < 3; j++) {

                if(i % 5 == 0) {
                    break;
                }
                board.addImage(UUID.randomUUID().toString(), i + "file" + j + ".jpg");
            }
            boardRepository.save(board);
        }   // end for
    }

    @Transactional
    @Test
    public void testSearchImageReplyCount() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        // 1. boardRepository.searchWithAll(null, null, pageable);

        // BoardListAllDTO = 조회하려고 하는 게시글 + 해당 게시글의 댓글 수 + 게시글에 달린 첨부파일
        // Page<BoardListAllDTO> = 페이지 요청 정보아 맞게 BoardListAllDTO를 조회해 온 결과 묶음 (0 페이지의 10건 데이터)
        Page<BoardListAllDTO> result = boardRepository.searchWithAll(null, null, pageable);

        log.info("-------------------------");
        log.info(result.getTotalElements());

        result.getContent().forEach(boardListAllDTO -> log.info(boardListAllDTO));
    }



}