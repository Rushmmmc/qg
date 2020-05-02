package com.zhangmengcong.www.util;



import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;


/**@author:zmc
 *@function: Jdbc的工具类
 */
public class JdbcUtil {

    private static String url;
    private static String user;
    private static String password;

    //静态变量才能被静态代码块访问


    static
    //文件读取,只需读取一次,用静态代码块 要给下面的方法用
    {
        //创建Properties比file简单
        Properties pro = new Properties();
        try {
            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");

            String path = null;
            if (res != null) {
                path = res.getPath();
            }
            if (path != null) {
                pro.load(new FileReader(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = pro.getProperty("url");
        user = pro.getProperty("user");
        password = pro.getProperty("password");
        String driver = pro.getProperty("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @return 配置后的Connection对象
     * @ throws SQLException
     */
    public static Connection getConnetction() throws SQLException//返回一个Connection对象
    {
        return DriverManager.getConnection(url,user,password);
    }

    public static void close(ResultSet rs, Statement stmt, Connection conn)//释放的方法
    {
        if(rs != null)
        {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null)
        {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt, Connection conn)//重载释放的方法
    {
        close(null,stmt,conn);
    }

}
