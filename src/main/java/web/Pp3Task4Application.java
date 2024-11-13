package web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

@SpringBootApplication
public class Pp3Task4Application {
    public static void main(String[] args) throws JsonProcessingException {
        User user1 = new User(3L, "James", "Brown", (byte) 30);
        User user2 = new User(3L, "Tomas", "Shelby", (byte) 30);
        UserService userService = new UserServiceImpl();
        userService.listUsers();
        System.out.println();
        userService.addUser(user1);
        System.out.println();
        userService.updateUser(user2);
        System.out.println();
        userService.deleteUser("3");

        SpringApplication.run(Pp3Task4Application.class, args);
    }
}
