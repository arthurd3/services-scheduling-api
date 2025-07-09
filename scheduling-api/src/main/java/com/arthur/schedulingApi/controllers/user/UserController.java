package com.arthur.schedulingApi.controllers.user;

import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.usecases.user.FindAllUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    private final FindAllUsers findAllUsers;

    public UserController(FindAllUsers findAllUsers) {
        this.findAllUsers = findAllUsers;
    }



    @GetMapping("/findAll")
    public ResponseEntity<Page<UserResponseDTO>> findAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(findAllUsers.findAllUsers(PageRequest.of(page, size)));
    }
}
