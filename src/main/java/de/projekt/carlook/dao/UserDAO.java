package de.projekt.carlook.dao;

import de.projekt.carlook.dao.entity.Account;
import de.projekt.carlook.dao.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO {

    public UserDAO() {
    }

    public User create(User user) {
        try {
            String sql = "INSERT INTO carlook.user(firstname, lastname, email) VALUES(?, ?, ?)";

            PreparedStatement statement = getPreparedStatement(sql);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getEmail());

            if(statement.execute()){
                return read(user.getEmail());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User read(String email) {
        try {
            String sql = "SELECT firstname, lastname, email FROM carlook.user WHERE email = ?";

            PreparedStatement statement = getPreparedStatement(sql);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setFirstname(resultSet.getString("firstname"));
                user.setLastname(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> readAll() {
        return null;
    }

    public User update(User user) {
        return null;
    }

    public boolean delete(String email) {
        return false;
    }
}
