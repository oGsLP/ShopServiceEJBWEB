package classes.service;

import classes.models.ShopCart;

import javax.ejb.Remote;
import java.util.List;

/**
 * @你大爷: XYF
 * @author: lenovo XYF
 * @Date: 2018/12/27
 * @Time: 10:41
 * @Package: classes.service
 */

@Remote
public interface ShopService {

    /**
     * 购买一系列goods
     * @param username
     * @param list
     * @return
     */
    String liquidateShopCart(String username, List<ShopCart> list);

    boolean checkAndSumList(List<ShopCart> list);

    double sumList(List<ShopCart> list);

    double getAfterDiscount(double sum);

}
