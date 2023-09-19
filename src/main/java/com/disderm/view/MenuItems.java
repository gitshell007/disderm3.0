package com.disderm.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MenuItems extends HttpServlet {

    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String result = "pepe";

        response.setContentType("application/json; charset=iso-8859-15");
        response.getOutputStream().println(result.toString());

    }
}
