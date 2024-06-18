package org.zerock.w1.calc;

import jdk.internal.org.jline.terminal.TerminalBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "calcController", urlPatterns = "/calc/makeResult")
public class CalcController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 브라우저에서 요청 시 보내준 데이터를 이름으로 찾을 수 있는 메서드 : getParametor("이름")
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");

        System.out.printf("num1 : %s num2 : %s", num1, num2);

        resp.sendRedirect("/index");
    }
}
