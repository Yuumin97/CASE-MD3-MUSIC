package rikkei.academy.controller;

import rikkei.academy.model.singer.Singer;
import rikkei.academy.service.singer.ISingerService;
import rikkei.academy.service.singer.SingerServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet({"/", "/singer"})
public class SingerController extends HttpServlet {
    private ISingerService singerService = new SingerServiceIMPL();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showFormCreateSinger(request, response);
                break;
            case "edit":
                showFormEditSinger(request,response);
                break;
            case "delete":
                fromDeleteSinger(request,response);
                break;
            case "detail":
                formDetailSinger(request,response);
                break;
            default:
                showListSinger(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                actionCreateSinger(request, response);
                break;
            case "edit":
                actionEditSinger(request, response);
                break;
            case "delete":
                actionDeleteSinger(request,response);
                break;
            default:
                actionSearchSinger(request,response);




        }
    }
    public void showListSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Singer> singerList = singerService.findAll();
        request.setAttribute("listSinger", singerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/list.jsp");
        dispatcher.forward(request, response);
    }
    public void showFormCreateSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/create.jsp");
        dispatcher.forward(request, response);
    }

    public void actionCreateSinger(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int birthday = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        Singer singer = new Singer(birthday,name,gender);
        singerService.save(singer);
        request.setAttribute("message", "Create Singer success !!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/singer/create.jsp");
        dispatcher.forward(request, response);
    }



}
