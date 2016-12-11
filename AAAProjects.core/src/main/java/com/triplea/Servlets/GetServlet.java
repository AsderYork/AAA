package com.triplea.Servlets;

/**
 * Created by Asder on 09.12.2016.
 */

import com.google.inject.Inject;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@WebServlet("/echo/get")

@Singleton

public class GetServlet extends HttpServlet {
    @InjectLogger Logger log;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String reqParameter = req.getParameter("id");
        if(reqParameter != null)  {
            PrintWriter out = resp.getWriter();

            out.print("<h1>"+reqParameter +"</h1>");
            log.info("So we recived Request on /get. Btw ReqParam is:" + reqParameter);
        }
    }

}