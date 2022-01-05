package xyz.crcismetm.blog.controller;

import xyz.crcismetm.blog.database.Database;
import xyz.crcismetm.blog.model.Article;
import xyz.crcismetm.blog.model.ArticleRepository;
import xyz.crcismetm.blog.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "article", value = "/article")
public class ArticleController extends HttpServlet {
    private ArticleRepository articleRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        Database database = (Database) config.getServletContext().getAttribute("database");
        articleRepository = new ArticleRepository(database);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        if(id == -2){
            System.out.println("find by title 1");
            String title = request.getParameter("title");
            request.setAttribute("articles",articleRepository.findByTitle(title));
        }else if(id == -1){
            request.setAttribute("articles",articleRepository.findAll());
        }else if(id > 0){
            Article article = articleRepository.findById(id);
            request.setAttribute("article",article);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession(false).getAttribute("user");
        Article article = new Article();
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setCommentable(Integer.parseInt(request.getParameter("commentable")));
        article.setUserID(user.getUsername());
        articleRepository.save(article);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession(false).getAttribute("user");
        Article article = new Article();
        article.setId(Integer.parseInt(request.getParameter("id")));
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setSubject(request.getParameter("subject"));
        article.setStatus(Integer.parseInt(request.getParameter("status")));
        article.setUserID(user.getUsername());
        articleRepository.saveOrUpdate(article);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("user");
        Article article = new Article();
        article.setId(Integer.parseInt(request.getParameter("id")));
        article.setUserID(user.getUsername());
        articleRepository.delete(article);
    }
}
