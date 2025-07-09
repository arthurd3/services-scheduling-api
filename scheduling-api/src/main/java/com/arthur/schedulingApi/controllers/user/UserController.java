package com.arthur.schedulingApi.controllers.user;

import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@RestController("/user")
public class UserController {

    public ResponseEntity<Pageable> getUsers() {

    }
}
