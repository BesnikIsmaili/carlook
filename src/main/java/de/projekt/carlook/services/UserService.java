package de.projekt.carlook.services;

import de.projekt.carlook.dao.UserDAO;
import de.projekt.carlook.dao.entity.User;
import de.projekt.carlook.exceptions.UserAlreadyExistsException;
import de.projekt.carlook.exceptions.UserNotExistsException;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }


    public User create(User user) throws UserAlreadyExistsException {
        User existUser = userDAO.read(user.getEmail());
        if(existUser != null){
            throw new UserAlreadyExistsException(user.getEmail());
        }
        return userDAO.create(user);
    }

    public User read(String email) throws UserNotExistsException {
        User user = userDAO.read(email);
        if(user == null) {
            throw new UserNotExistsException(email);
        }
        return user;
    }

    public List<User> readAll() {
        return userDAO.readAll();
    }

    public User update(User user) {
        return userDAO.update(user);
    }

    public boolean delete(String email) {
        return userDAO.delete(email);
    }
}
