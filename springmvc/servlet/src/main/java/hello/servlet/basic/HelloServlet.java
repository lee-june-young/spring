package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);


        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); //euckr?은 옛날 -> 요즘은 다 utf-8 사용!
        response.getWriter().write("hello "+username); //write()한거는 http message body에 들어간다. 위에 2개는 content-type 헤더정보에 들어가는거다.
    }



}
