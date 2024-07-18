package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.b01.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // @Param : 메서드 파라미터를 쿼리의 명명된 파라미터(:bno 부분)에 바인딩
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(@Param("bno") Long bno, Pageable pageable);

    // 특정 게시물(bno)에 해당하는 댓글 삭제 -- 쿼리 메서드
    void deleteByBoard_Bno(Long bno);
}
