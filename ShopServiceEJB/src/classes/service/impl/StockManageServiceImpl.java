package classes.service.impl;

import classes.dao.StockDao;
import classes.entities.Goods;
import classes.service.StockManageService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @你大爷: XYF
 * @author: lenovo XYF
 * @Date: 2018/12/26
 * @Time: 21:28
 * @Package: classes.service.impl
 */

@Stateless
public class StockManageServiceImpl implements StockManageService {

    @EJB
    StockDao stockDao;

    @Override
    public Goods findGoods(int id) {
        Goods goods=null;
        String name= stockDao.getName(id);
        if(name!=null){
            goods=findGoods(name);
        }
        return goods;
    }

    @Override
    public Goods findGoods(String name) {
        return stockDao.findGoods(name);
    }

    @Override
    public List getStock() {
        return stockDao.getStock();
    }

    @Override
    public boolean addStock(Goods goods) {
        return false;
    }

    @Override
    public boolean updateGoods(Goods goods) {
        return stockDao.updateGoods(goods);
    }

    @Override
    public boolean deleteGoods(String name) {
        return false;
    }

    @Override
    public int getGoodsNum() {
        return stockDao.getGoodsNum();
    }

    @Override
    public boolean buyGoods(String name, int num) {
//        Goods goods=DaoFactory.getStockDao().findGoods(name);
//        if(goods!=null){
//            int stockNum=goods.getNum();
//            if(stockNum<num){
//                return false;
//            }
//            else {
//                goods.setNum(stockNum-num);
//                updateGoods(goods);
//                return true;
//            }
//        }
//        else
            return false;
    }

}
