package org.zerock.w1.todo;

import org.zerock.w1.todo.dto.TodoDTO;
import org.zerock.w1.todo.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list")
public class TodoListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("/todo/list");
        
        // TodoService.INSTANCE 객체를 생성 -> .getList() 목록 불러오는 메서드 실행
        // getList() 반환 값을 저장할 수 있는 변수 List<TodoDTO> dtoList
        List<TodoDTO> dtoList = TodoService.INSTANCE.getList();
        
        // dtoList(=불러온 목록)을 JSP(view단)으로 전달할 수 있도록 setAttribute() 사용
        // setAttribute("이름", 전달할 데이터)
        req.setAttribute("list", dtoList);
        
        // 해당 페이지로 request 객체와 response 객체 전달
        req.getRequestDispatcher("/WEB-INF/todo/list.jsp")
                .forward(req, resp);
    }
}
