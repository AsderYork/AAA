package com.triplea;

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

@WebServlet("/echo/get")
public class GetServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String reqParameter = req.getParameter("id");
        if(reqParameter != null)  {
            PrintWriter out = resp.getWriter();

            out.print("<h1>"+reqParameter +"</h1>");
        }
    }

}