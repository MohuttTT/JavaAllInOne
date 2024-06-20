package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.w2.dao.TodoDAO;
import org.zerock.w2.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTests {

    private TodoDAO todoDAO;

    @BeforeEach
    public void ready() {
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception {
        System.out.println("select now() 실행" + todoDAO.getTime());
    }
    @Test
    public void testTime2() throws Exception {
        System.out.println("select now() 실행" + todoDAO.getTime());
    }

    @Test
    public void testInsert() throws Exception {
        TodoVO todoVO = TodoVO.builder().title("sample Title...").dueDate(LocalDate.of(2021,12,31)).build();

        todoDAO.insert(todoVO);
    }
    @Test
    public void testList() throws Exception {
        List<TodoVO> list = todoDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }
    // 조회 기능 테스트
    @Test
    public void testSelectOne() throws Exception {

        Long tno = 1L;
        TodoVO vo = todoDAO.selectOne(tno);
        System.out.println(vo);
    }

    //수정 기능 테스트
    @Test
    public void testUpdateOne() throws Exception {
        TodoVO todoVO = TodoVO.builder().tno(1L).title("Sample Title...").dueDate(LocalDate.of(2021,12,31)).finished(true).build();
        todoDAO.updateOne(todoVO);

    }
    // 특정 삭제 기능 테스트
    @Test
    public void testDeleteOne() throws Exception {
        TodoVO todoVO = TodoVO.builder().tno(7L).title("Sample Title...").dueDate(LocalDate.of(2021,12,31)).finished(true).build();
        todoDAO.deleteOne(7L);
    }

    // 특정 글 삭제
//    @Test
//    public void deleteOne(Long tno) throws Exception {
//        String sql = "delete from tbl_todo where tno = ?";
//
//        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
//        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
//
//        preparedStatement.setLong(1, tno);
//
//        int result = preparedStatement.executeUpdate();
//
//        return result;
//    }
    // 특정 게시글 삭제2

//    @Test
//    public void testDeleteOne2() throws Exception {
//
//        // 마지막 번호 조회 : tbl_todo 테이블에서 가장 최근에 추가된 게시글 번호를 조회 쿼리
//        // limit 1 : 쿼리 결과로 하나의 행만 가져온다.
//        String sql = "select tno from tbl_todo order by tno desc limit 1;";
//        @Cleanup PreparedStatement pstmt = ConnectionUtil.INSTANCE.getConnection()
//                .prepareStatement(sql);
//        @Cleanup ResultSet rs = pstmt.executeQuery();
//        rs.next();
//
//        Long tno = rs.getLong("tno");
//
//        System.out.println(tno + "번의 데이터 " + todoDAO.deleteOne(tno) + "행이 삭제되었습니다.");
//
//    /* Long tno = 3L; // 반드시 존재하는 번호를 이용하여 테스트 진행
//    todoDAO.deleteOne(tno); */
//    //}
}
