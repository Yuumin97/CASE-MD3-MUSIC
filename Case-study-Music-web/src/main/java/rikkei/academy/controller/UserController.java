package rikkei.academy.controller;

import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;
import rikkei.academy.model.account.User;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.io.*;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/users")
public class UserController extends HttpServlet {

    private IRoleService roleService = new RoleServiceIMPL();
    private IUserService userService = new UserServiceIMPL();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "register":
                showFormRegister(request, response);
                break;
            case "login":
                showFormLogin(request, response);
                break;
            case "logout":
                logOut(request, response);
                break;
            case "change_avatar":
                showUpLoadAvatar(request, response);
                break;
            case "profile":
                showMyProfile(request, response);
                break;
            case "change_pass":
                showFormChangePass(request, response);
                break;
            case "change_profile":
                showFormChangeProfile(request,response);
                break;
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
            case "register":
                actionRegister(request, response);
                break;
            case "login":
                actionLogin(request, response);
                break;
            case "change_avatar":
                actionUpLoadAvatar(request, response);
                break;
            case "change_pass":
                try {
                    actionChangePass(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "change_profile" :
                try {
                    actionChangeProfile(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

        }
    }

    public void destroy() {
    }

    public void showFormRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
        dispatcher.forward(request, response);
    }

    public void actionRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = "user";
        Set<String> strRole = new HashSet<>();
        Set<Role> roles = new HashSet<>();
        strRole.add(role);
        strRole.forEach(role1 -> {
            switch (role1) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN);
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM);
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER);
                    roles.add(userRole);
            }
        });
        System.out.println("roles set ---> " + roles);

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        if (userService.existedByUsername(username)) {
            request.setAttribute("message", "The username existed! Please try again!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
//            return;
        }
        String email = request.getParameter("email");
        if (userService.existedByEmail(email)) {
            request.setAttribute("message", "The email existed! Please try again!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
//            return;
        }
        String password = request.getParameter("password");
        String confirm_pass = request.getParameter("repeat_pass");
        if (!password.equals(confirm_pass)) {
            request.setAttribute("message", "The password do not match!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
            dispatcher.forward(request, response);
            return;
        }
        User user = new User(name, username, email, password, roles);
        userService.save(user);
        request.setAttribute("success", "Create user success!!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/register.jsp");
        dispatcher.forward(request, response);
    }

    //LOGIN
    public void showFormLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/form-login/login.jsp");
        dispatcher.forward(request, response);
    }

    public void actionLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.findByUsernameAndPassword(username, password);

        String pageJSP = "";
        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            System.out.println("get userlogin ---> " + session.getAttribute("user"));
            pageJSP = "WEB-INF/profile/profile.jsp";
//            Cookie cookieName = new Cookie("username",username);
//            Cookie cookiePass = new Cookie("password",password);
////            cookieName.setMaxAge(10000);
////            cookiePass.setMaxAge(10000);
////            response.addCookie(cookieName);
////            response.addCookie(cookiePass);

        } else {

            pageJSP = "WEB-INF/form-login/login.jsp";
        }
        request.setAttribute("message", "Wrong username or password");
        RequestDispatcher dispatcher = request.getRequestDispatcher(pageJSP);
        dispatcher.forward(request, response);
    }

    //LOG OUT
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
    }

    //CHANGE AVATAR
    public void showUpLoadAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/upload/upload_avatar.jsp");
        requestDispatcher.forward(request, response);
    }

    public void actionUpLoadAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String avatar = request.getParameter("avatar");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        userService.changeAvatar(avatar, id);
        request.setAttribute("avatar", avatar);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/profile/profile.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showMyProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/profile/profile.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showFormChangePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/form-login/change_pass.jsp");
        requestDispatcher.forward(request, response);
    }

    public void actionChangePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        User userLogin = (User) request.getSession().getAttribute("user");

        String oldPass = request.getParameter("old-pass");
        String newPass = request.getParameter("new-pass");
        String repeatPass = request.getParameter("repeat-pass");

        if (!newPass.equals(repeatPass)) {
            request.setAttribute("message", "Repeat password not match");
            request.getRequestDispatcher("WEB-INF/form-login/change_pass.jsp").forward(request, response);
            return;
        }

        if (!userLogin.getPassword().equals(oldPass)) {
            request.setAttribute("message", "Old password not match");
            request.getRequestDispatcher("WEB-INF/form-login/change_pass.jsp").forward(request, response);
            return;
        }

        userLogin.setPassword(newPass);
        userService.update( userLogin);
        request.getSession().setAttribute("user", userLogin);

        request.setAttribute("success", "Change password success");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    public void showFormChangeProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/form-login/change_profile.jsp");
        requestDispatcher.forward(request, response);
    }
    public void actionChangeProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        User userLogin = (User) request.getSession().getAttribute("user");

        String oldName = request.getParameter("oldName");
        String newName = request.getParameter("newName");
        String oldEmail = request.getParameter("oldEmail");
        String newEmail = request.getParameter("newEmail");
        if (oldName.equals(newName)) {
            request.setAttribute("message", "New name must be different");
            request.getRequestDispatcher("WEB-INF/form-login/change_profile.jsp").forward(request, response);
            return;
        }
        if (newEmail.equals(oldEmail)) {
            request.setAttribute("message", "New email must be different");
            request.getRequestDispatcher("WEB-INF/form-login/change_profile.jsp").forward(request, response);
            return;
        }
        userLogin.setName(newName);
        userLogin.setEmail(newEmail);
        userService.update(userLogin);
        request.getSession().setAttribute("user", userLogin);

        request.setAttribute("success", "Change profile success");
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}