package xyz.crcismetm.blog.controller;

import xyz.crcismetm.blog.database.Database;
import xyz.crcismetm.blog.model.ArticleRepository;
import xyz.crcismetm.blog.model.MathArticle;
import xyz.crcismetm.blog.model.MathArticleRepository;
import xyz.crcismetm.blog.utils.Identifier;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "math", value = "/math")
@MultipartConfig
public class Math extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id == null){
            request.getRequestDispatcher("/MathArticles").include(request,response);
            request.getRequestDispatcher("/view/MathPage.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/MathArticles").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/MathArticles").forward(request,response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/MathArticles").include(req,resp);
    }
}
