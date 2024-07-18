package org.zerock.b01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardListAllDTO {  // 게시판 목록 처리 DTO (게시물, 해당 게시물의 첨부파일, 댓글 개수

    private Long bno;               // 게시글 번호

    private String title;           // 게시글 제목

    private String writer;          // 게시글 작성자

    private LocalDateTime regDate;  // 게시글 등록 일자

    private Long replyCount;        // 해당 게시글의 댓글 수

    private List<BoardImageDTO> boardImages; // 게시글의 첨부파일들 (첨부파일 객체가 여러개이니 List로 나타냄)
}
