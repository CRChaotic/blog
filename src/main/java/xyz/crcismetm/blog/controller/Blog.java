package xyz.crcismetm.blog.controller;

import xyz.crcismetm.blog.database.Database;
import xyz.crcismetm.blog.model.Article;
import xyz.crcismetm.blog.model.User;
import xyz.crcismetm.blog.model.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "blog", value = "/blog")
public class Blog extends HttpServlet {
    private UserRepository userRepository;
    private User user;

    @Override
    public void init(ServletConfig config) throws ServletException {
        Database database = (Database) config.getServletContext().getAttribute("database");
        userRepository = new UserRepository(database);
        user = new User();
        user.setUsername("Ryan");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("article");
        if (id == null) {
            request.getRequestDispatcher("/article?id=-1").include(request, response);
            request.getRequestDispatcher("/view/blog.jsp").forward(request, response);
        } else if (Integer.parseInt(id) > 0) {
            request.getRequestDispatcher("/article?id="+id).include(request, response);
            request.getRequestDispatcher("/view/blog_detail.jsp").forward(request, response);
        }else if(Integer.parseInt(id) == -2){
            request.getRequestDispatcher("/article?id=-2").include(request, response);
            request.getRequestDispatcher("/view/blog.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/article").include(request, response);
        response.sendRedirect(request.getContextPath()+"/blog");
    }
}
