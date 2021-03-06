package xyz.crcismetm.blog.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AccessUser",urlPatterns = {"/user/*","/article","/space","/MathArticles"})
public class Access implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if(req.getSession().getAttribute("user") != null){
            chain.doFilter(request, response);
        }else{
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
