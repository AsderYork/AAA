package com.triplea.Servlets;
/**
 * Created by Asder on 09.12.2016.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/echo/*")
public class FilterServlet extends HttpServlet {

    private boolean isURLRight(HttpServletRequest req){
        String url = req.getRequestURI();
        if(url.matches("^\\/echo\\/((((get)|(post))[^/\\s]*$)|$)"))
        {
            return true;
        }
        return false;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        if(isURLRight(req)){
            out.print("<h1>Tyup!</h1>");
            out.print("<h1>Hello Servlet\n+"+req.getRequestURI()+"</h1>");
        }
        else  {
            resp.sendError(404);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        if(isURLRight(req)){
            out.print("<h1>Tyup!</h1>");
            out.print("<h1>Hello Servlet\n+"+req.getRequestURI()+"</h1>");
        }
        else  {
            resp.sendError(404);
        }
    }

}