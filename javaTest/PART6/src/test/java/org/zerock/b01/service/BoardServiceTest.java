package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // -- Spring Boot App-Context를 로드하여 통합 테스트 수행
@Log4j2
class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegisterWithImages() {

        log.info(boardService.getClass().getName());

        // 사용자 요청 데이터 (게시글 내용)
        BoardDTO boardDTO = BoardDTO.builder()
                .title("File...Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        // 사용자 요청 데이터 (게시글 첨부 파일)
        boardDTO.setFileNames(
                Arrays.asList(
                        UUID.randomUUID() + "_aaa.jpg",
                        UUID.randomUUID() + "_bbb.jpg",
                        UUID.randomUUID() + "_ccc.jpg"
                ));

        // 사용자 요청 데이터(boardDTO)를 인자값으로 받으면 register에서는 dtoToEntity()를 사용하여 boardDTO를 repository에서 사용할 수 있는 Board Entity 객체로 변환한 후 register의 save()(insert or update)를 호출
        Long bno = boardService.register(boardDTO);

        log.info("bno: " + bno);
    }

    @Test
    public void testRegister() {
        log.info(boardService.getClass().getName());

        BoardDTO boardDTO = BoardDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user00")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info("bno : {}", bno);
    }

    // 게시물 수정 처리
    @Test
    public void testModify() {
        // 변경 필요한 데이터만
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("Update...101")
                .content("Update Content 101...")
                .build();

        // 첨부파일을 하나 추가
        boardDTO.setFileNames(Arrays.asList(UUID.randomUUID() + "_zzz.jpg"));

        boardService.modify(boardDTO);
    }

    @Test
    public void testList() { // -- 목록/검색 기능 확인
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .type("tcw")
                .keyword("1")
                .page(1)
                .size(10)
                .build();
        // -- 제목 혹은 내용 혹은 작성자가 '1'이라는 문자열을 가진 데이터를 검색하고 페이징 처리

        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);
    }

    @Test
    public void testReadAll() {

        Long bno = 101L;    // 실제 있는 게시물 번호로 조회해야 함

        BoardDTO boardDTO = boardService.readOne(bno);

        log.info(boardDTO);

        for (String fileName : boardDTO.getFileNames()) {
            log.info(fileName);
        }// end for
    }

    @Test
    public void testRemoveAll() {

        Long bno = 1L;

        boardService.remove(bno);
    }

    @Test
    public void testListWithAll() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<BoardListAllDTO> responseDTO = boardService.listWithAll(pageRequestDTO);

        List<BoardListAllDTO> dtoList = responseDTO.getDtoList();

        dtoList.forEach(boardListAllDTO -> {
            log.info(boardListAllDTO.getBno() + ":" + boardListAllDTO.getTitle());

            if(boardListAllDTO.getBoardImages() != null) {
                for (BoardImageDTO boardImage : boardListAllDTO.getBoardImages()) {
                    log.info(boardImage);
                }
            }

            log.info("------------------------------------");
        });
    }
}