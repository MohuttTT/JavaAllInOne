package org.zerock.b01.service;

import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public interface BoardService {
    Long register(BoardDTO boardDTO); // -- 등록

    BoardDTO readOne(Long bno); // -- 특정 게시물 조회

    void modify(BoardDTO boardDTO); // -- 수정 (기존 엔티티 객체에서 필요한 부분만 변경)

    void remove(Long bno); // -- 삭제

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    // old) 게시글 목록 = 게시글 + 해당 게시글의 댓글 수
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    // new! 게시글 목록 = 게시글 + 해당 게시글의 첨부 파일 + 해당 게시글의 댓글 수
    PageResponseDTO<BoardListAllDTO> listWithAll(PageRequestDTO pageRequestDTO);

    // BoardDTO 객체물 -> Board Entity 객체로 변환하는 메서드
    default Board dtoToEntity(BoardDTO boardDTO) {

        // Entity 객체 생성 -- Entity 객체의 값은 BoardDTO의 값으로 설정
        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();

        /* 파일 이름 처리 및 이미지 추가 */
        if (boardDTO.getFileNames() != null) {  // 파일 이름의 리스트가 존재하는 경우
            boardDTO.getFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_"); // 파일 이름 언더스코어로 분류(uuid, fileName 분리)한 배열 생성
                board.addImage(arr[0], arr[1]); // uuid와 fileName을 BoardImage 객체로 추가
            });
        }
        return board;
    }

    default BoardDTO entityToDTO(Board board) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .build();

        List<String> fileNames =
                board.getImageSet().stream().sorted().map(boardImage ->
                        boardImage.getUuid() + "_" + boardImage.getFileName()).collect(Collectors.toList());

        boardDTO.setFileNames(fileNames);

        return boardDTO;
    }

}
