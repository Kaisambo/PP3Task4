package web.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://94.198.50.185:7081/api/users";
    private String sessionId;
    private String token;
    @Override
    public ResponseEntity<String> listUsers() {
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        List<String> cookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);
        if (cookies != null && !cookies.isEmpty()) {
            sessionId = cookies.get(0).split(";")[0]; // Получаем только саму строку sessionId
        }
        System.out.println(responseEntity);
        return responseEntity;
    }

    @Override
    public ResponseEntity<String> addUser(User user) {
        HttpHeaders headers1 = new HttpHeaders();
        headers1.setContentType(MediaType.APPLICATION_JSON);
        headers1.set(HttpHeaders.COOKIE, sessionId);

        String requestJson = String.format("{\"id\": \"3\", \"name\": \"%s\", \"lastName\": \"%s\", \"age\": %d}",
                 user.getName(), user.getLastName(), user.getAge());

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers1);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        token = responseEntity.getBody();

        System.out.println(token);
        System.out.println(responseEntity);

        return responseEntity;
    }

    @Override
    public ResponseEntity<String> updateUser(User user) {
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.set(HttpHeaders.COOKIE, sessionId);

        String requestJson = String.format("{\"id\": \"3\", \"name\": \"%s\", \"lastName\": \"%s\", \"age\": %d}",
                user.getName(), user.getLastName(), user.getAge());

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers2);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);

        token += responseEntity.getBody();

        System.out.println(token);
        System.out.println(responseEntity);
        System.out.println("Response Body: " + responseEntity.getBody());

        return responseEntity;
    }

    @Override
    public ResponseEntity<String> deleteUser(String id) {
        HttpHeaders headers3 = new HttpHeaders();
        headers3.setContentType(MediaType.APPLICATION_JSON);
        headers3.set(HttpHeaders.COOKIE, sessionId);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers3);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, requestEntity, String.class);

        token += responseEntity.getBody();

        System.out.println(token);
        System.out.println(responseEntity);
        System.out.println("Response Body: " + requestEntity.getBody());

        return responseEntity;
    }
}
