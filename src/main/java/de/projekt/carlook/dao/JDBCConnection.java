package de.projekt.carlook.dao;

import java.sql.*;
import java.util.Properties;

public class JDBCConnection {

    private final String url = "jdbc:postgresql://dumbo.inf.h-brs.de/bismai2s"; // TODO

    private static JDBCConnection instance;

    private Connection connection;

    public static JDBCConnection getInstance(){
        if(instance == null){
            instance = new JDBCConnection();
        }
        return instance;
    }

    public void openConnection(){
        try {
            DriverManager.registerDriver(new org.postgresql.Driver() );
            Properties props = new Properties();
            props.setProperty("user", "bismai2s"); // TODO
            props.setProperty("password", "bismai2s"); // TODO
            connection = DriverManager.getConnection(this.url,props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if(!connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

}
