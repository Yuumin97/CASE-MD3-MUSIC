package rikkei.academy.controller.song;


import rikkei.academy.model.song.Song;
import rikkei.academy.service.song.ISongService;
import rikkei.academy.service.song.SongServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet({"/song"})
public class SongController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public SongController() {
        super();
    }
    private ISongService songService = new SongServiceIMPL();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateSong(request, response);
                 break;
            default:
                showSongList(request, response);
                break;

        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "create":
                actionCreateSong(request, response);
                break;

        }
    }

    private void actionCreateSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int listen = Integer.parseInt(request.getParameter("listen"));
        String img = request.getParameter("avatar");
        String audio = request.getParameter("mp3");
        Song song = new Song(name, listen,img,audio);
        songService.save(song);
        request.setAttribute("message", "Create Song success !!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/song/create.jsp");
        dispatcher.forward(request, response);


    }

    private void showListSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Song> songList = songService.findAll();
        request.setAttribute("song", songList);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
    private void showCreateSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/song/create.jsp");
        dispatcher.forward(request, response);
    }
    private void showSongList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        SongServiceIMPL serviceIMPL = new SongServiceIMPL();
        List<Song> list = serviceIMPL.findAllSongs((page-1)*recordsPerPage,
                recordsPerPage);
        int noOfRecords = serviceIMPL.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("song", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("test/index.jsp");
        view.forward(request, response);
    }
}
