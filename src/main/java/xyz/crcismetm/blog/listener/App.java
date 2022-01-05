package xyz.crcismetm.blog.listener;

import xyz.crcismetm.blog.database.Database;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class App implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public App() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String url = context.getInitParameter("databaseURL");
        String user = context.getInitParameter("databaseUser");
        String password = context.getInitParameter("databasePassword");
        Database database = new Database(url,user,password);
        context.setAttribute("database",database);
        String mathArticlesStoredPath = context.getInitParameter("MathArticlesStoredPath");
        context.setAttribute("MathArticlesStoredPath",mathArticlesStoredPath);
        String temporaryPath = context.getInitParameter("TemporaryPath");
        context.setAttribute("TemporaryPath",temporaryPath);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
