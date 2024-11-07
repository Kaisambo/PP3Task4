package web.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Setter
@Getter
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/api/users")
    public ResponseEntity<String> GetUsers() {
    return userService.listUsers();
    }

    @PostMapping(value = "/api/users")
    public ResponseEntity<String> CreateUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping(value = "/api/users")
    public ResponseEntity<String> UpdateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable("id") String userId) {
        return userService.deleteUser(userId);
    }
}
