package service.impl;

import dao.CategoryDAO;
import dao.impl.CategoryDAOImpl;
import domain.Category;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import service.CategoryService;
import utils.JedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author LOL_toulan
 * @Time 2020/1/23 0:59
 * @Message
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    /**
     * 查询所有旅游信息
     *
     * @return
     */
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> categoryList = null;
        if (categorys == null || categorys.size() == 0) {
            categoryList = categoryDAO.findAll();
            for (int i = 0; i < categoryList.size(); i++) {
                System.out.println("正在从数据库查询。。。");
                jedis.zadd("category", categoryList.get(i).getCid(), categoryList.get(i).getCname());
            }
        } else {
            System.out.println("正在从redis中查询。。。");
            categoryList = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
