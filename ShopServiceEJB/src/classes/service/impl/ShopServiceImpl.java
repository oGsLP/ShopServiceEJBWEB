package classes.service.impl;

import classes.dao.StockDao;
import classes.dao.UserDao;
import classes.entities.Goods;
import classes.entities.User;
import classes.models.ShopCart;
import classes.service.ShopService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @你大爷: XYF
 * @author: lenovo XYF
 * @Date: 2018/12/27
 * @Time: 14:26
 * @Package: classes.service.impl
 */
@Stateless
public class ShopServiceImpl implements ShopService {

    @EJB
    UserDao userDao;
    @EJB
    StockDao stockDao;


    @Override
    public String liquidateShopCart(String username,List<ShopCart> list) {
        String message="No goods chosen";
        if(list.size()!=0){
            // 打折优惠处理
            double sum=getAfterDiscount(sumList(list));
            User user= userDao.findUser(username);
            double account=user.getAccount();
            if(account<sum){
                message="Account not enough";
            }
            else {
                user.setAccount(account-sum);
                userDao.updateUser(user);
                for(ShopCart shopCart:list)
                    buyGoods(shopCart);
                message="succeed";
            }
        }
        return message;
    }

    @Override
    public boolean checkAndSumList(List<ShopCart> list){
        boolean check=true;
        for(ShopCart shopCart: list){
            if(checkAndSum(shopCart)<0){
                check=false;
                break;
            }
        }
        return check;
    }

    @Override
    public double sumList(List<ShopCart> list){
        double result=0;
        for(ShopCart shopCart:list){
            result+= stockDao.findGoods(shopCart.getName()).getPrice()*shopCart.getNum();
        }
        return result;
    }

    @Override
    public double getAfterDiscount(double sum) {
        if(sum>10000){
            if(sum>50000){
                if(sum>100000){
                    sum-=(14000+(sum-100000)*0.25);
                }
                else {
                    sum-=(4000+(sum-50000)*0.2);
                }
            }
            else {
                sum-=(sum-10000)*0.1;
            }
        }
        return sum;
    }

    private double checkAndSum(ShopCart shopCart){
        double value;
        int num=shopCart.getNum();
        Goods goods= stockDao.findGoods(shopCart.getName());
        int sum=goods.getNum();
        if(sum<num){
            value=-1;
        }
        else {
            value=num*goods.getPrice();
        }
        return value;
    }



    private void buyGoods(ShopCart shopCart){
        Goods goods= stockDao.findGoods(shopCart.getName());
        goods.setNum(goods.getNum()-shopCart.getNum());
        stockDao.updateGoods(goods);
    }
}
