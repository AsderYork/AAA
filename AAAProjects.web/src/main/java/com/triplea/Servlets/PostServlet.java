package com.triplea.Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Asder on 09.12.2016.
 */
@WebServlet("/echo/post")
public class PostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String path = "http://localhost:8080/echo/get";
        path = path + "?id=" + req.getParameter("id");

        resp.sendRedirect(path);
    }
}
