package classes.dao.impl;

import classes.dao.StockDao;
import classes.entities.Goods;
import classes.util.MYSQLHelper;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.sql.*;
import java.util.List;

/**
 * @你大爷: XYF
 * @author: lenovo XYF
 * @Date: 2018/12/26
 * @Time: 21:23
 * @Package: classes.dao.impl
 */
@Stateless
public class StockDaoImpl implements StockDao {

//    private MYSQLHelper mysqlHelper=MYSQLHelper.getInstance();
    @PersistenceContext
    protected EntityManager manager;

    @Override
    public String getName(int id) {
        return manager.find(Goods.class,id).getName();
//        Connection connection=mysqlHelper.getConnection();
//        PreparedStatement pstmt=null;
//        ResultSet result=null;
//
//        String name=null;
//
//        try {
//            pstmt=connection.prepareStatement("select name from goods where id=?");
//            pstmt.setInt(1,id);
//            result=pstmt.executeQuery();
//            if(result.next()){
//                name=result.getString("name");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            mysqlHelper.closeResultSet(result);
//            mysqlHelper.closePreparedStatement(pstmt);
//            mysqlHelper.closeConnection(connection);
//        }
//        return name;
    }

    @Override
    public Goods findGoods(String name) {
        Query query=manager.createQuery("select g from Goods g where g.name=?1");
        query.setParameter(1,name);
        Goods goods=null;
        if(query.getResultList()!=null)
            goods= (Goods) query.getResultList().get(0);
//        Connection connection=mysqlHelper.getConnection();
//        PreparedStatement pstmt=null;
//        ResultSet result=null;
//
//        Goods goods=null;
//
//        try {
//            pstmt=connection.prepareStatement("select * from goods where name=?");
//            pstmt.setString(1,name);
//            result=pstmt.executeQuery();
//            if(result.next()){
//                goods=new Goods();
//                goods.setGoods(result);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            mysqlHelper.closeResultSet(result);
//            mysqlHelper.closePreparedStatement(pstmt);
//            mysqlHelper.closeConnection(connection);
//        }
        return goods;
    }

    @Override
    public List getStock() {
        Query query=manager.createQuery("select g from Goods g");
        List goodsList= query.getResultList();
        manager.clear();

//        Connection connection=mysqlHelper.getConnection();
//        Statement statement=null;
//        ResultSet result=null;
//        try {
//            statement=connection.createStatement();
//            result=statement.executeQuery("select * from goods");
//            while (result.next()){
//                Goods goods=new Goods();
//                goods.setGoods(result);
//                goodsList.add(goods);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            mysqlHelper.closeResultSet(result);
//            mysqlHelper.closeStatement(statement);
//            mysqlHelper.closeConnection(connection);
//        }
        return goodsList;
    }

    @Override
    public boolean addStock(Goods goods) {
        if(goods!=null){
            manager.persist(goods);
//            Connection connection=mysqlHelper.getConnection();
//            PreparedStatement pstmt=null;
//            try {
//                pstmt=connection.prepareStatement("insert into goods(name,kind,price,num) values(?,?,?,?)");
//                setGoodsExID(goods, pstmt);
//                pstmt.executeUpdate();
//                connection.commit();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            finally {
//                mysqlHelper.closePreparedStatement(pstmt);
//                mysqlHelper.closeConnection(connection);
//            }
            return true;
        }
        else
            return false;
    }



    @Override
    public boolean updateGoods(Goods goods) {
        if(goods!=null){
            manager.merge(goods);
//            Connection connection=mysqlHelper.getConnection();
//            PreparedStatement pstmt=null;
//            try {
//                pstmt=connection.prepareStatement("update goods set name=?,kind=?,price=?,num=? where id=?");
//                setGoodsExID(goods, pstmt);
//                pstmt.setInt(5,goods.getId());
//                pstmt.executeUpdate();
//                connection.commit();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            finally {
//                mysqlHelper.closePreparedStatement(pstmt);
//                mysqlHelper.closeConnection(connection);
//            }
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteGoods(String name) {
        if(name!=null){
            Goods goods=findGoods(name);
            if(goods!=null){
                manager.remove(goods);
                return true;
            }
            else
//            Connection connection=mysqlHelper.getConnection();
//            PreparedStatement pstmt=null;
//            try {
//                pstmt=connection.prepareStatement("delete from goods where name=?");
//                pstmt.setString(1,name);
//                pstmt.executeUpdate();
//                connection.commit();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            finally {
//                mysqlHelper.closePreparedStatement(pstmt);
//                mysqlHelper.closeConnection(connection);
//            }
            return false;
        }
        else
            return false;
    }

    @Override
    public int getGoodsNum() {
        return getStock().size();
    }



    private void setGoodsExID(Goods goods, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1,goods.getName());
        pstmt.setString(2,goods.getKind());
        pstmt.setDouble(3,goods.getPrice());
        pstmt.setInt(4,goods.getNum());
    }

}
