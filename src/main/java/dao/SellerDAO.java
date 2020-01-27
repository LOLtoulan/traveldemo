package dao;

import domain.Seller;

/**
 * @Author LOL_toulan
 * @Time 2020/1/26 12:56
 * @Message
 */
public interface SellerDAO {

    Seller findSellerBySid(int sid);
}
