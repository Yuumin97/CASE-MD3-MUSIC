package rikkei.academy.controller.song;


import rikkei.academy.model.music.Category;
import rikkei.academy.model.singer.Band;
import rikkei.academy.model.singer.Singer;
import rikkei.academy.model.song.Song;
import rikkei.academy.service.band.BandServiceIMPL;
import rikkei.academy.service.band.IBandService;
import rikkei.academy.service.category.CategoryServiceIMPL;
import rikkei.academy.service.category.ICategoryService;
import rikkei.academy.service.singer.ISingerService;
import rikkei.academy.service.singer.SingerServiceIMPL;
import rikkei.academy.service.song.ISongService;
import rikkei.academy.service.song.SongServiceIMPL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet({"/song"})
public class SongController extends HttpServlet {
    private ICategoryService categoryService = new CategoryServiceIMPL();
    private ISingerService singerService = new SingerServiceIMPL();
    private IBandService bandService = new BandServiceIMPL();
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
            case "playsong":
                actionSong(request, response);
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
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.findById(categoryId);
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        String[] singerId = request.getParameterValues("singer");
        List<Integer> singerIdList = new ArrayList<>();
        for (int i = 0; i < singerId.length; i++) {
            singerIdList.add(Integer.parseInt(singerId[i]));
        }
        String[] bandId = request.getParameterValues("singer");
        List<Integer> bandIdList = new ArrayList<>();
        for (int i = 0; i < bandId.length; i++) {
            singerIdList.add(Integer.parseInt(singerId[i]));
        }
        String name = request.getParameter("name");
        int listen = Integer.parseInt(request.getParameter("listen"));
        String img = request.getParameter("avatar");
        String audio = request.getParameter("mp3");
        Song song = new Song(name,listen,img,audio,categories,singerIdList,bandIdList);
        songService.save(song);
        request.setAttribute("message", "Create Song success !!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/song/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("category", categoryList);
        List<Singer> singerList= singerService.findAll();
        request.setAttribute("singer", singerList);
        List<Band> bandList = bandService.findAll();
        request.setAttribute("band", bandList);
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
    public void actionSong(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Song song = songService.findById(id);
        request.setAttribute("song", song);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/song/playSong.jsp");
        dispatcher.forward(request, response);
    }
}
