package com.housie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.housie.model.InputData;
import com.housie.util.TicketGenerator;

public class HousieController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String VIEW = "/view.jsp";

    public HousieController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InputData input = new InputData();
        input.setSheet(request.getParameter("sheet"));
        PrintWriter out = response.getWriter();
        out=TicketGenerator.generateSheets(input, out);
        out.close(); // close the output stream
    }
}