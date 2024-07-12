package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// TodoModifyController의 구현
@WebServlet(name = "todoModifyController", value = "/todo/modify")
@Log4j2
public class TodoModifyController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE; //TodoService클래스의 인스턴스 초기화
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //날짜 형식 설정

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{
            Long tno = Long.parseLong(req.getParameter("tno")); // tno를 Long 타입으로 변환
            TodoDTO todoDTO = todoService.get(tno); // todoService를 사용하여 tno에 해당하는 TodoDTO 객체 가져오기
            req.setAttribute("dto", todoDTO); // todoDTO 객체를 요청 속성 dto로 설정
            req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req, resp);
        }catch(Exception e){
            log.error(e.getMessage()); // 예외 메시지 로그로 기록
            throw new ServletException("modify get ... error"); // 새로운 servleException 발생 시켜 에러 처리
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String finishedStr = req.getParameter("finished"); //파라미터 finished의 값을 문자열로 가지고 오기

        TodoDTO todoDTO = TodoDTO.builder().tno(Long.parseLong(req.getParameter("tno"))).title(req.getParameter("title")).dueDate(LocalDate.parse(req.getParameter("dueDate"),DATEFORMATTER)).finished(finishedStr != null && finishedStr.equals("on")).build();
        // TodoDTO 객체를 빌터 패턴을 사용하여 생성 

        log.info("/todo/modify Post...");
        log.info(todoDTO);
        try{
            todoService.modify(todoDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list");
    }
}
