package com.skillsup.service;

import com.skillsup.dao.UserFileDao;
import com.skillsup.dao.UserDAO;
import com.skillsup.dao.UserInMemDAO;
import com.skillsup.model.Response;
import com.skillsup.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    public static final String EMPTY_USERNAME_OR_AND_PASSWORD = "empty username or/and password ";
    public static final String YOU_HAVE_WEAK_PASSWORD = "You have weak password";
    public UserDAO userDao = new UserInMemDAO();
    public UserFileDao userFileDao = new UserFileDao();
    Pattern passwordPattern =  Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");


    @Override
    public Response<User> login(String username, String pass) {
        if(username == null || pass == null || username.equals("") || pass.equals(""))
        {
            return new Response<>(null, false, "empty username or/and password ");
        }

        User user = userDao.get(username);
        if(user == null)
        {
            return new Response<>(null, false, "user doesnt exist! ");
        }
        if (!pass.equals(user.getPassword())) {
            return new Response<>(null, false, "wrong pass! ");
        }
        return new Response<>(user, true, "");
    }

    @Override
    public Response<User> register(User user) {
        if(user == null ||
                user.getUsername() == null ||
                user.getPassword() == null ||
                user.getUsername().equals("") ||
                user.getPassword().equals(""))
        {
            return new Response<>(null, false, EMPTY_USERNAME_OR_AND_PASSWORD);
        }

        User existingUser = userDao.get(user.getUsername());
        if(existingUser != null)
        {
            return new Response<>(null, false, "user already exists! ");
        }

        Matcher m = passwordPattern.matcher(user.getPassword());
        if(!m.find())
        {
            return new Response<>(null, false, YOU_HAVE_WEAK_PASSWORD + " , password must:" +
                    "    be at least 8 chars\n" +
                    "    Contains at least one digit\n" +
                    "    Contains at least one lower alpha char and one upper alpha char\n" +
                    "    Contains at least one char within a set of special chars (@#%$^ etc.)\n" +
                    "    Does not contain space, tab, etc.\n ");
        }
        userDao.save(user);
        return new Response<>(user, true, "");
    }
}
