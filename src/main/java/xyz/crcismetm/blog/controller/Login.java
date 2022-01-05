package xyz.crcismetm.blog.controller;

import xyz.crcismetm.blog.database.Database;
import xyz.crcismetm.blog.model.User;
import xyz.crcismetm.blog.model.UserRepository;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet {
    private UserRepository userRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        Database database = (Database)config.getServletContext().getAttribute("database");
        userRepository = new UserRepository(database);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        List<User> who = userRepository.find(user);
        if(who.size() == 1){
            request.getSession().setAttribute("user",who.get(0));
            response.sendRedirect(request.getContextPath()+"/space");
        }else{
            request.getSession().setAttribute("message","Did you forget your password? What the f**k is wrong with you?");
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }
}
