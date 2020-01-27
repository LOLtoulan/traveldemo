package service;

import domain.PageBean;
import domain.Route;

/**
 * @Author LOL_toulan
 * @Time 2020/1/23 20:11
 * @Message
 */
public interface RouteService {

    /**
     * 根据类别进行分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    Route findOne(int rid);


}
