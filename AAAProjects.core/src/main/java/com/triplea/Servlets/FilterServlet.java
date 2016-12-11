package com.triplea.Servlets;
/**
 * Created by Asder on 09.12.2016.
 */

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

//@WebServlet("/echo/*")
@Singleton
public class FilterServlet extends HttpServlet {
    @InjectLogger
    Logger log;

    private boolean isURLRight(HttpServletRequest req){
        log.info("So we've been asked to check correctness of "+req.getRequestURI()+"url");
        String url = req.getRequestURI();
        if(url.matches("^\\/echo\\/((((get)|(post))[^/\\s]*$)|$)"))
        {
            log.info("It's correct");
            return true;
        }
        log.info("It's incorrect");
        return false;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        log.info("We recived GET request on"+req.getRequestURI());
        if(isURLRight(req)){
            log.info("And we respond to it");
            out.print("<h1>Tyup!</h1>");
            out.print("<h1>Hello Servlet\n+"+req.getRequestURI()+"</h1>");
        }
        else  {
            log.info("But we leave it as it's 404");
            resp.sendError(404);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        log.info("We recived POST request on"+req.getRequestURI());
        if(isURLRight(req)){
            log.info("And we respond to it");
            out.print("<h1>Tyup!</h1>");
            out.print("<h1>Hello Servlet\n+"+req.getRequestURI()+"</h1>");
        }
        else  {
            log.info("But we leave it as it's 404");
            resp.sendError(404);
        }

    }

}