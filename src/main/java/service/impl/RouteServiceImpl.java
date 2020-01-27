package service.impl;

import dao.*;
import dao.impl.*;
import domain.*;
import org.apache.commons.beanutils.BeanUtils;
import service.RouteService;

import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2020/1/23 20:12
 * @Message
 */
public class RouteServiceImpl implements RouteService {

    private RouteDAO dao = new RouteDAOImpl();

    private RouteImgDAO imgDAO = new RouteImgDAOImpl();

    private SellerDAO sellerDAO = new SellerDAOImpl();

    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    private FavoriteDAO favoriteDAO = new FavoriteDAOImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {

        PageBean<Route> pageBean = new PageBean<Route>();

        //设置当前页码
        pageBean.setCurrentPage(currentPage);
        //设置每页显示条数
        pageBean.setPageSize(pageSize);

        //获取总记录数
        int totalCount = dao.totalCount(cid, rname);
        pageBean.setTotalCount(totalCount);

        //获取当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;
        List<Route> list = dao.findByPage(cid, start, pageSize, rname);
        pageBean.setList(list);
        //获取总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        System.out.println("pageBean : " + pageBean.getList());
        return pageBean;
    }

    @Override
    public Route findOne(int rid) {


        Route route = dao.findOne(rid);
        List<RouteImg> routeImgList = imgDAO.findImgByRid(rid);
        route.setRouteImgList(routeImgList);

        int sid = route.getSid();

        Seller seller = sellerDAO.findSellerBySid(sid);

        route.setSeller(seller);

        int cid = route.getCid();

        Category category = categoryDAO.findCategory(cid);

        route.setCategory(category);

        int count = favoriteDAO.findCountByRid(route.getRid());

        route.setCount(count);

        return route;
    }
}
