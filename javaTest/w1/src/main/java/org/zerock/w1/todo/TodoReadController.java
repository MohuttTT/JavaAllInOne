package org.zerock.w1.todo;

import org.zerock.w1.todo.dto.TodoDTO;
import org.zerock.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/read");

        // /todo/read?tno=123으로 요청이 들어온 경우
        // 전달된 데이터(쿼리 스트링)의 tno 이름으로 값을 얻어냄
        // 요청으로 전달된 데이터의 타입은 String이기 때문에 Long 타입으로 변환
        Long tno = Long.parseLong(req.getParameter("tno"));
        
        // Service단의 조회 메서드(get)를 호출하여 해당하는 글 번호의 정보를 dto 객체로 받음
        TodoDTO dto = TodoService.INSTANCE.get(tno);
        
        // JSP가 조회한 글을 출력할 수 있도록 setAttribute()를 이용하여 dto 값을 셋팅
        req.setAttribute("dto", dto);
        
        // /todo/read.jsp가 요청을 받아 브라우저에 화면을 응답할 수 있도록 forward 사용
        req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req,resp);
    }
}
