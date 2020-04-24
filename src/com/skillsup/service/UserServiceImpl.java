package com.skillsup.service;

import com.skillsup.dao.UserDAO;
import com.skillsup.dao.UserInFileDaoImpl;
import com.skillsup.dao.UserInMemDaoImpl;
import com.skillsup.model.Response;
import com.skillsup.model.User;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    public static final String EMPTY_USERNAME_OR_AND_PASSWORD = "There are empty username or/and password! ";
    public static final String YOU_HAVE_WEAK_PASSWORD = "You have weak password! ";
    public static final String SUCH_USER_DOESNOT_EXIST = "Such user does not exist! ";
    public static final String IT_IS_WRONG_PASS = "It's wrong password! ";
    public static final String SUCH_USER_ALREADY_EXISTS = "Such user already exists! ";
    Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    @Override
    public Response<User> login(String username, String pass, String choiceUsersDB) {
        if (username == null || pass == null || username.equals("") || pass.equals("")) {
            return new Response<>(null, false, EMPTY_USERNAME_OR_AND_PASSWORD);
        }
        User user = choiceUsersDB.equals("0") ? new UserInMemDaoImpl().getUser(username) : new UserInFileDaoImpl().getUser(username);
        if (user == null) {
                return new Response<>(null, false, SUCH_USER_DOESNOT_EXIST);
            }
            if (!pass.equals(user.getPassword())) {
                return new Response<>(null, false, IT_IS_WRONG_PASS);
            }
            return new Response<>(user, true, "");
    }

    @Override
    public Response<User> register(User user, String choiceUsersDB) {
        if (user == null ||
                user.getUsername() == null ||
                user.getPassword() == null ||
                user.getUsername().equals("") ||
                user.getPassword().equals("")) {
            return new Response<>(null, false, EMPTY_USERNAME_OR_AND_PASSWORD);
        }
        Matcher m = passwordPattern.matcher(user.getPassword());
        if (!m.find()) {
            return new Response<>(null, false, YOU_HAVE_WEAK_PASSWORD + " , password must:" +
                    "    be at least 8 chars\n" +
                    "    Contains at least one digit\n" +
                    "    Contains at least one lower alpha char and one upper alpha char\n" +
                    "    Contains at least one char within a set of special chars (@#%$^ etc.)\n" +
                    "    Does not contain space, tab, etc.\n ");
        }
        UserDAO userDao = choiceUsersDB.equals("0") ? new UserInMemDaoImpl() : new UserInFileDaoImpl();
        User existingUser = userDao.getUser(user.getUsername());
            if (existingUser != null) {
                return new Response<>(null, false, SUCH_USER_ALREADY_EXISTS);
            }
            userDao.saveUser(user);
            return new Response<>(user, true, "User's info is registered/updated!");
    }

    @Override
    public Response<User> delete(User user, String choiceUsersDB) {
        UserDAO userDao = choiceUsersDB.equals("0") ? new UserInMemDaoImpl() : new UserInFileDaoImpl();
        userDao.deleteUser(user);
        return new Response<>(user, true, "User's deleted!");
    }

    @Override
    public Response<User> changeAccountBalance(User user, String choiceUsersDB) {
        UserDAO userDao = choiceUsersDB.equals("0") ? new UserInMemDaoImpl() : new UserInFileDaoImpl();
        userDao.saveUser(user);
        return new Response<>(user, true, "Account balance's changed!");
    }

    @Override
    public void printAllUsers(String choiceUsersDB) {
        UserDAO userDao = choiceUsersDB.equals("0") ? new UserInMemDaoImpl() : new UserInFileDaoImpl();
        Collection<User> userList = userDao.getUserList();
        userList.forEach(System.out::println);
    }
}
