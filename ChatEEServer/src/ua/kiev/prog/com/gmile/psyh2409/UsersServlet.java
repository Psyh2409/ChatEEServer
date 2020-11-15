package ua.kiev.prog.com.gmile.psyh2409;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersServlet extends HttpServlet {
    private UserSet userSet = UserSet.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println(userSet.toJSON());
    }
}
