package de.projekt.carlook.services;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import de.projekt.carlook.dao.AccountDAO;
import de.projekt.carlook.dao.UserDAO;
import de.projekt.carlook.dao.entity.Account;
import de.projekt.carlook.dao.entity.User;
import de.projekt.carlook.exceptions.AuthFailureException;
import de.projekt.carlook.exceptions.UserAlreadyExistsException;

public class AuthService {

    private AccountDAO accountDAO;

    private UserDAO userDAO;

    public AuthService() {
        accountDAO = new AccountDAO();
        userDAO = new UserDAO();
    }


    public boolean checkAuthentication(Account account) throws AuthFailureException {
        Account existAccount = accountDAO.read(account.getEmail());

        if(existAccount == null || !account.getEmail().equals(existAccount.getEmail())
                || !account.getPassword().equals(existAccount.getPassword())){
            throw new AuthFailureException();
        }else {
            return true;
        }
    }

    public User register(User user, Account account) throws UserAlreadyExistsException {
        Account existAccount = accountDAO.read(account.getEmail());
        if (existAccount != null) {
            throw new UserAlreadyExistsException(account.getEmail());
        }

        accountDAO.create(account);

        return userDAO.create(user);
    }


}
