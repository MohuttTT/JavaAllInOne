package org.zerock.w1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// /sample이라는 경로를 호출하면 실행될 서블릿
@WebServlet(name = "sampleServlet", urlPatterns = "/sample")
public class SampleServlet extends HttpServlet {

    // HTTP Method가 GET 요청일 때 실행될 메서드 doGet()
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet..." + this);
    }

    // 서블릿 객체가 처음 만들어질 때 실행되는 초기회 메서드 init()
    @Override
    public void init() throws ServletException {
        System.out.println("init(ServletConfig).............");
    }

    // 서블릿 객체가 소멸될 때 실행되는 메서드 destroy()
    @Override
    public void destroy() {
        System.out.println("destroy.............");
    }
}
