package de.projekt.carlook.dao;

import de.projekt.carlook.dao.entity.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDAO extends AbstractDAO {


    public boolean create(Account account) {
        try {
            String sql = "INSERT INTO carlook.account(email, password) VALUES(?, ?)";

            PreparedStatement statement = getPreparedStatement(sql);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());

            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Account read(String email) {
        try {
            String sql = "SELECT email, password FROM carlook.account WHERE email = ?";

            PreparedStatement statement = getPreparedStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Account account = new Account(resultSet.getString("email"),
                                                resultSet.getString("password"));
                return account;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Account> readAll() {
        return null;
    }

    public Account update(Account user) {
        return null;
    }

    public boolean delete(String email) {
        return false;
    }
}
