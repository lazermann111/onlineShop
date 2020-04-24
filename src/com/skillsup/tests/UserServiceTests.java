package com.skillsup.tests;

import com.skillsup.model.Response;
import com.skillsup.model.User;
import com.skillsup.model.UserRole;
import com.skillsup.service.UserService;
import com.skillsup.service.UserServiceImpl;

import static com.skillsup.service.UserServiceImpl.EMPTY_USERNAME_OR_AND_PASSWORD;
import static com.skillsup.service.UserServiceImpl.YOU_HAVE_WEAK_PASSWORD;

public class UserServiceTests {

    public static void main(String[] args) {
        testUserRegistrationUniqueness();
        testNullEmptyPasswordLogin();
        testWeakPassword();
        testLoginUseCases();

        System.out.println("all test have passed successfully!");
    }

    private static void testUserRegistrationUniqueness()
    {
        //Given
        UserService userService = new UserServiceImpl();
        User user = new User("abc", "1qa@WS3edFRWDF", UserRole.CUSTOMER);

        //When
        Response<User> res = userService.register(user);
        //Then
        assert res.isSuccess() : "Unique user haven't registered!";

        //When
        Response<User> res2 = userService.register(user);
        //Then
        assert !res2.isSuccess() : "non-unique user registered!";
    }

    private static void testWeakPassword(){
        UserService userService = new UserServiceImpl();
        User user = new User("abc2", "asd", UserRole.CUSTOMER);

        Response<User> res2 = userService.register(user);

        assert !res2.isSuccess() : "Weak pass user have been registered!";
        assert res2.getFinalMessage().contains(YOU_HAVE_WEAK_PASSWORD) : "somme weird weird msg : " + res2.getFinalMessage();

    }
    private static void testNullEmptyPasswordLogin()
    {
        UserService userService = new UserServiceImpl();
        User user = new User("abc", null, UserRole.CUSTOMER);
        Response<User> res = userService.register(user);

        assert !res.isSuccess() : "Unique user haven't registered!";
        assert EMPTY_USERNAME_OR_AND_PASSWORD.equals(res.getFinalMessage()) : "somme weird weird msg : " + res.getFinalMessage();

    }

    private static void testLoginUseCases()
    {
        UserService userService = new UserServiceImpl();
        String login = "abcasdh!#";
        String password = "1qa@WS3ed";
        User user = new User(login, password, UserRole.CUSTOMER);
        Response<User> res = userService.register(user);

        assert res.isSuccess() : res.getFinalMessage();

        Response<User> res2 = userService.login(login, "");
        assert !res2.isSuccess();

        Response<User> res3 = userService.login(login, password);
        assert res3.isSuccess();
    }
}
