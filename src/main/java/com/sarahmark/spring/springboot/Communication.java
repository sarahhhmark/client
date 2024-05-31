package com.sarahmark.spring.springboot;

import com.sarahmark.spring.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private final RestTemplate restTemplate;

    private final HttpHeaders httpHeaders;

    private String cookies;

    private final String URL = "http://94.198.50.185:7081/api/users";

    @Autowired
    public Communication(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
    }

    public List<User> allUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate
                .exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        this.cookies = responseEntity.getHeaders().getFirst("Set-Cookie");
        System.out.println(cookies);
        System.out.println(responseEntity);
        List<User> users = responseEntity.getBody();
        return users;
    }

//    public User getUser(long id) {
//        httpHeaders.add("Cookie", cookies);
//        HttpEntity<User> requestEntity = new HttpEntity<>(httpHeaders);
//        ResponseEntity<User> responseEntity = restTemplate
//                .exchange(URL + "/" + id, HttpMethod.GET, requestEntity,
//                        new ParameterizedTypeReference<User>() {
//                        });
//        return responseEntity.getBody();
//    }

    public void addUser(User user) {
        httpHeaders.add("Cookie", cookies);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, httpHeaders);
        String res = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();

        System.out.println(res);
    }

    public void saveUser(User user) {
        httpHeaders.add("Cookie", cookies);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, httpHeaders);
        String res = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody();

        System.out.println(res);
    }

    public void deleteUser(long id) {
        httpHeaders.add("Cookie", cookies);
        HttpEntity<User> requestEntity = new HttpEntity<>(httpHeaders);
        String res = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, requestEntity, String.class).getBody();

        System.out.println(res);
    }
}

