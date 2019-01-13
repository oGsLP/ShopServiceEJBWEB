package classes.servlets;

import classes.service.StockManageService;
import classes.helper.JSONHelper;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @你大爷: XYF
 * @author: lenovo XYF
 * @Date: 2018/12/23
 * @Time: 12:06
 * @Package: ${PACKAGE_NAME}
 */
@WebServlet("/app/listLayout")
public class ListLayoutServlet extends HttpServlet {
    @EJB
    StockManageService stockManageService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List goods= stockManageService.getStock();
        String goods_Json = JSONHelper.toJson(goods);
        System.out.println("Find "+goods.size()+" goods");
        request.setAttribute("stock_list",goods_Json);
        request.getRequestDispatcher("/app/stockList.jsp").forward(request,response);
    }
}
