package rikkei.academy.controller;

import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.io.*;
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
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
                actionLogin(request,response);
                break;
        }

    }

    public void destroy() {
    }

    public void showFormRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login/register.jsp");
        dispatcher.forward(request, response);
    }

    public void actionRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = "admin";
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
                case "user":
                    Role userRole = roleService.findByName(RoleName.USER);
                    roles.add(userRole);
            }
        });
        System.out.println("roles set ---->" + roles);
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        if (userService.existedByUsername(username)) {
            request.setAttribute("message", "The username existed! Please try again !");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login/register.jsp");
            dispatcher.forward(request, response);
        }
        String email = request.getParameter("email");
        if (userService.existedByEmail(email)) {
            request.setAttribute("message", "The email existed! Please try again !");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login/register.jsp");
            dispatcher.forward(request, response);
        }
        String password = request.getParameter("password");
        System.out.println("password----->" + password);
        String confirm_pass = request.getParameter("repeat_password");
        System.out.println("confirm pass -----> " + confirm_pass);
        if(!password.equals(confirm_pass)){
            request.setAttribute("message", "The password do not match!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login/register.jsp");
            dispatcher.forward(request,response);
            return;
        }
        User user = new User(name, username, email, password, roles);
        userService.save(user);
        request.setAttribute("success", "Create success");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login/login.jsp");
        dispatcher.forward(request, response);
    }
    public void showFormLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login/login.jsp");
        dispatcher.forward(request, response);
    }
    public void actionLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            request.getRequestDispatcher("/login/login.jsp").forward(request, response);
            return;
        }

        User user = userService.checkLogin(username, password);

        RoleName roleName = user.getRoleName();

        request.getSession().setAttribute("role", roleName);
        request.getSession().setAttribute("userLogin", user);

        request.getRequestDispatcher("/home/index.jsp").forward(request, response);
    }

}