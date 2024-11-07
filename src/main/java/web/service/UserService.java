package web.service;

import org.springframework.http.ResponseEntity;
import web.model.User;

public interface UserService {
    ResponseEntity<String> listUsers();
    ResponseEntity<String> addUser(User user);
    ResponseEntity<String> updateUser(User user);
    ResponseEntity<String> deleteUser(String id);

}
