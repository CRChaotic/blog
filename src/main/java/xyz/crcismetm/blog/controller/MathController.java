package xyz.crcismetm.blog.controller;

import xyz.crcismetm.blog.database.Database;
import xyz.crcismetm.blog.model.MathArticle;
import xyz.crcismetm.blog.model.MathArticleRepository;
import xyz.crcismetm.blog.model.User;
import xyz.crcismetm.blog.utils.Identifier;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "MathArticles", value = "/MathArticles")
public class MathController extends HttpServlet {
    private MathArticleRepository mathArticleRepository;
    private String mathArticlesStoredPath;
    private String tempPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        Database database = (Database) config.getServletContext().getAttribute("database");
        mathArticleRepository = new MathArticleRepository(database);
        mathArticlesStoredPath = (String)config.getServletContext().getAttribute("MathArticlesStoredPath");
        tempPath = (String)config.getServletContext().getAttribute("TemporaryPath");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if(id != null && mathArticleRepository.findById(id) != null){
            File file = new File(mathArticlesStoredPath+"/"+id);
            response.setContentType("text/html");
            response.setContentLengthLong(file.length());
            InputStream in = new FileInputStream(file);
            OutputStream out =  response.getOutputStream();
            byte[] data = new byte[1024];
            while (in.read(data) != -1){
                out.write(data);
            }
            out.flush();
            out.close();
            in.close();
        }else{
            request.setAttribute("articles",mathArticleRepository.findByDeleted(0));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("operation").equals("delete")){
            doDelete(request,response);
            response.sendRedirect(request.getContextPath()+"/space");
        }else if(request.getParameter("operation").equals("save")){
            try {
                Part files = request.getPart("file");
                if (files.getSize() < 1) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
                File file = File.createTempFile("math", ".tmp", new File(tempPath));
                Identifier identifier = new Identifier("SHA3-256");
                InputStream in = files.getInputStream();
                FileOutputStream out = new FileOutputStream(file);
                byte[] data = new byte[1024];
                int left = 0;
                while ((left=in.read(data)) != -1) {
                    if(left < 1024){
                        out.write(data,0,left);
                    }else{
                        out.write(data);
                    }
                    identifier.read(data);
                }
                out.flush();
                out.close();
                in.close();
                String id = identifier.getUniqueId();// very important,cannot get it twice or more
                MathArticle article = new MathArticle();
                article.setId(id);
                // check out if it existed
                if (mathArticleRepository.findById(id) == null && file.renameTo(new File(mathArticlesStoredPath + "/" + id))) {
                    // save to database
                    article.setTitle(request.getParameter("title"));
                    article.setSubtitle(request.getParameter("subtitle"));
                    article.setOwner(((User)request.getSession(false).getAttribute("user")).getUsername());
                    mathArticleRepository.save(article);
                } else if (file.delete()) {
                    article.setDeleted(0);
                    MathArticle old = new MathArticle();
                    old.setId(article.getId());
                    mathArticleRepository.update(article,old);
                    System.out.println("File deleted:"+file.getAbsolutePath());
                }
                response.sendRedirect(request.getContextPath()+"/space");
            } catch (IOException | ServletException e) {
                System.out.println(e.getMessage());
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(mathArticleRepository.findById(id) != null){
            mathArticleRepository.temporarilyDeleteById(id);
        }
    }
}
