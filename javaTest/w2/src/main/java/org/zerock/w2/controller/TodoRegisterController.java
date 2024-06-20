package org.zerock.w2.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w2.dto.TodoDTO;
import org.zerock.w2.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoRegisterController", value = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {

    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register GET.......");

        //session 활용하여 로그인 정보가 있는지 없는지 판단
        //session 객체는 HttpServletRequest 객체의 getSession()메서드 사용
        // Session Repository 안에 있는 JSEESIONID의 값을 키로 사용하여 동일한 session 객체를 반환
        // 해당하는 session 객체를 찾지 못한다면 새로운 객체를 생성해서 반환
        HttpSession session = req.getSession();

        // 해당 객체가 ㅂ새로만들어진 객체인지 아닌지 판단: isNew()
        if(session.isNew()) { // JSESSIONIS가 없는 새로운 사용자 
        log.info("JSESSIONID 쿠키가 새로만들어진 사용자 ");
        resp.sendRedirect("/login");
        return;
        }

        // 로그인을 한적이 없는 사용자
        if(session.getAttribute("loginIfo") == null ){
            log.info("로그인정보가 없는 사용자");
            resp.sendRedirect("/login");
            return;
        }

        // 로그인한 자격이 확인 되는 경우 => 할일 등록 페이지로 이동
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDTO todoDTO = TodoDTO.builder().title(req.getParameter("title")).dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER)).build();

        log.info("/todo/register POST.......");
        log.info(todoDTO);
        try{
            todoService.register(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list");

    }
}
