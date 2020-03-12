package com.skillsup.sluzhba;

import com.skillsup.dao.UserDAO;
import com.skillsup.dao.UserInMemDAO;
import com.skillsup.model.Response;
import com.skillsup.model.User;
import com.skillsup.model.UserRole;

public class UserServiceImpl implements UserService {

    private UserDAO userDao = new UserInMemDAO();

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
}
