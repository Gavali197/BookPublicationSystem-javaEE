import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.faces.context.FacesContext; // not used here - avoid
import java.io.IOException;
import java.util.Map;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getRequestURI();

        boolean loginPage = url.contains("login.xhtml");
        boolean resourceFile = url.contains("javax.faces.resource");

        if (loginPage || resourceFile) {
            chain.doFilter(req, res);
            return;
        }

        // safer: use session map
        Object loginBeanObj = request.getSession().getAttribute("loginBean");

        if (loginBeanObj == null) {
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
            return;
        }

        // cast and continue
        com.bookpub.controller.LoginBean loginBean = (com.bookpub.controller.LoginBean) loginBeanObj;
        if (loginBean.getLoggedUser() == null) {
            response.sendRedirect(request.getContextPath() + "/login.xhtml");
            return;
        }

        // role checks
        String role = loginBean.getLoggedUser().getRole();
        if ("ADMIN".equals(role) && url.contains("/author/")) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard.xhtml");
            return;
        }
        if ("AUTHOR".equals(role) && url.contains("/admin/")) {
            response.sendRedirect(request.getContextPath() + "/author/dashboard.xhtml");
            return;
        }

        chain.doFilter(req, res);
    }
}
