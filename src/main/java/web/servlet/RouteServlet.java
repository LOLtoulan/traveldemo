package web.servlet;

import dao.RouteDAO;
import dao.impl.RouteDAOImpl;
import domain.PageBean;
import domain.Route;
import domain.User;
import service.FavoriteService;
import service.RouteService;
import service.impl.FavoriteServiceImpl;
import service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author LOL_toulan
 * @Time 2020/1/23 20:10
 * @Message
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();


    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //接受rname 线路名称
        String rname = request.getParameter("rname");
        //rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        int cid = 0;
        int pageSize;
        int currentPage;
        if (cidStr != null && cidStr.length() > 0&& !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        PageBean<Route> routePageBean = service.pageQuery(cid, currentPage, pageSize, rname);

        System.out.println("routePageBean : "+routePageBean);

        writeValue(routePageBean, response);

    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int rid = Integer.parseInt(request.getParameter("rid"));

        RouteService service = new RouteServiceImpl();

        Route route = service.findOne(rid);

        writeValue(route, response);
    }


    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ridStr = request.getParameter("rid");
        int rid = 0;
        if (ridStr != null && ridStr.length() > 0&& !"null".equals(ridStr)) {
            rid = Integer.parseInt(ridStr);
        }
        User user = (User) request.getSession().getAttribute("user");

        int uid = 0;
        if (user == null) {
            response.sendRedirect("login.html");
        }
        if (user != null) {
            uid = user.getUid();
        }

        boolean favorite = favoriteService.isFavorite(rid, uid);

        writeValue(favorite, response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rid = Integer.parseInt(request.getParameter("rid"));

        User user = (User) request.getSession().getAttribute("user");

        int uid;
        if (user == null) {
           return;
        }
        uid = user.getUid();

        favoriteService.addFavorite(rid, uid);
    }
}
