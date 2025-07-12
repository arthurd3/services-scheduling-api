package com.arthur.schedulingApi.controllers.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.user.response.ApiResponseDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.usecases.user.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/v1/users")
public class UserController {

    private final FindAllUsers findAllUsers;
    private final RegisterUser registerUser;
    private final FindUser findUser;
    private final DeleteUser deleteUser;
    private final EditUser editUser;


    public UserController(FindAllUsers findAllUsers, RegisterUser registerUser, FindUser findUser, DeleteUser deleteUser, EditUser editUser) {
        this.findAllUsers = findAllUsers;
        this.registerUser = registerUser;
        this.findUser = findUser;
        this.deleteUser = deleteUser;
        this.editUser = editUser;
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

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<UserResponseDTO>> findAllUsers(@PathVariable Long id ) {
        var userResponse = findUser.findUserAsDto(id);

        if(userResponse.isPresent())
            return ResponseEntity.ok(userResponse);

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDTO> deleteUser(@PathVariable Long id) {
        boolean deleted = deleteUser.deleteUser(id);

        if (deleted)
            return ResponseEntity.ok(new ApiResponseDTO("Usuário com id "+ id +" deletado com sucesso!"));

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDTO("Usuário com id " + id + " não encontrado."));

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Optional<UserResponseDTO>> editUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        var editedUser = editUser.editUser(id , userRequestDTO);

        if(editedUser.isPresent())
            return ResponseEntity.ok(editedUser);

        return ResponseEntity.notFound().build();
    }
}
