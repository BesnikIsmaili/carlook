package de.projekt.carlook.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;

public abstract class AbstractDAO {

    private JDBCConnection connection;

    public AbstractDAO() {
        connection = JDBCConnection.getInstance();
        connection.openConnection();
    }

    public Statement getStatement() {
        return connection.getStatement();
    }

    public PreparedStatement getPreparedStatement(String sql) {

        return connection.getPreparedStatement(sql);
    }
}
