package iss.repository.utils;

import java.io.IOException;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private Properties jdbcProps;

    public DbUtils(String file) {
        jdbcProps = new Properties();
        try {
            jdbcProps.load(getClass().getResourceAsStream(file));
            jdbcProps.list(System.out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection instance=null;

    private Connection getNewConnection(){
        String url=jdbcProps.getProperty("jdbc.url");
        String user=jdbcProps.getProperty("jdbc.user");
        String pass=jdbcProps.getProperty("jdbc.pass");

        Connection con=null;
        try {
            if (user!=null && pass!=null)
                con= DriverManager.getConnection(url,user,pass);
            else
                con=DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();
        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return instance;
    }
}
