package com.triplea.Servlets;
import com.triplea.Servlets.GuiceInjection.InjectLogger;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Asder on 09.12.2016.
 */
//@WebServlet("/echo/post")

@Singleton
public class PostServlet  extends HttpServlet {
    @InjectLogger
    Logger log;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        String path = "http://localhost:8080/echo/get";
        path = path+"?id="+req.getParameter("id");
        log.info("So we recived Request on /post. Redirecting it to Get with parameter" + req.getParameter("id"));
       resp.sendRedirect(path);
    }
}
