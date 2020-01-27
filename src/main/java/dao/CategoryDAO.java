package dao;

import domain.Category;

import java.util.List;

/**
 * @Author LOL_toulan
 * @Time 2020/1/23 0:58
 * @Message
 */
public interface CategoryDAO {
    List<Category> findAll();

    Category findCategory(int cid);
}
