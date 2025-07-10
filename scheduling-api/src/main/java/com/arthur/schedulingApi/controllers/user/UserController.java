package com.arthur.schedulingApi.controllers.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.usecases.user.FindAllUsers;
import com.arthur.schedulingApi.usecases.user.RegisterUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/users")
public class UserController {

    private final FindAllUsers findAllUsers;
    private final RegisterUser registerUser;

    public UserController(FindAllUsers findAllUsers, RegisterUser registerUser) {
        this.findAllUsers = findAllUsers;
        this.registerUser = registerUser;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDTO>> findAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(findAllUsers.findAllUsers(PageRequest.of(page, size)));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(registerUser.registerUser(userRequestDTO));
    }
}
