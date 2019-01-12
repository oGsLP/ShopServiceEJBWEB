package classes.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Hashtable;
import java.util.Properties;

/**
 * @你大爷: XYF
 * @author: lenovo XYF
 * @Date: 2018/12/26
 * @Time: 19:47
 * @Package: classes.util
 */
public class MYSQLHelper {
    private static MYSQLHelper instance=new MYSQLHelper();
    private Connection con=null;
    private DataSource dataSource=null;

    private MYSQLHelper(){


        InitialContext jndiContext;
        Properties properties = new Properties();
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        try {
            jndiContext = new InitialContext(properties);
            dataSource = (DataSource) jndiContext.lookup("java:/MySqlDS");
            if (dataSource==null)
                System.out.println("datasource null");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        System.out.println("got context");
        System.out.println("About to get ds---DaoHelper");
    }

    public static MYSQLHelper getInstance(){
        return instance;
    }
    public Connection getConnection() {
        try {
            con=dataSource.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return MYSQLHelper.getInstance().con;
    }


    // close connection ,resultSet and other resources


    public void closeConnection(Connection connection){
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void closeResultSet(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void closePreparedStatement(PreparedStatement preparedStatement){
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void closeStatement(Statement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
