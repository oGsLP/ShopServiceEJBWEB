package classes.servlets;

import classes.service.UserManageService;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @你大爷: XYF
 * @author: lenovo XYF
 * @Date: 2018/12/22
 * @Time: 14:07
 * @Package: classes.servlets
 */
@WebServlet("/app/login")
public class LoginServlet extends HttpServlet {

    @EJB
    UserManageService userManageService;

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if(userManageService.checkUserName(username)){
            if(userManageService.checkUserPassword(username,password)){
                HttpSession session = request.getSession(true);
                session.setAttribute("username", username);

                ServletContext servletContext=getServletContext();
                int counter= Integer.parseInt((String) servletContext.getAttribute("user-counter")) ;
                counter++;
                servletContext.setAttribute("user-counter",counter+"");
                response.sendRedirect(request.getContextPath()+"/app/listLayout");
            }
            else {
                request.getRequestDispatcher("/app/wrongPassword.jsp").forward(request,response);
            }
        }
        else {
            request.getRequestDispatcher("/app/wrongUser.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        if(session!=null){
            String username= (String) session.getAttribute("username");
            if(username!=null){
                res.sendRedirect("/app/listLayout");
            }
            else {
                req.getRequestDispatcher("/app/login.jsp").forward(req,res);
            }
        }
        else {
            req.getRequestDispatcher("/app/login.jsp").forward(req,res);
        }
    }
}
