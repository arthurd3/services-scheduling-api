package com.arthur.schedulingApi.controllers.user;

import com.arthur.schedulingApi.controllers.user.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.ApiResponseDTO;
import com.arthur.schedulingApi.controllers.user.response.UserResponseDTO;
import com.arthur.schedulingApi.usecases.user.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Page<UserResponseDTO>> findAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                              @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(findAllUsers.findAllUsers(PageRequest.of(page, size)));
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        var newUser = registerUser.registerUser(userRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.id()).toUri();

        return ResponseEntity.created(location).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserResponseDTO>> findAllUsers(@PathVariable Long id ) {
        return ResponseEntity.ok(Optional.of(findUser.findUserAsDto(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> deleteUser(@PathVariable Long id) {
        deleteUser.deleteUser(id);
        return ResponseEntity.ok(new ApiResponseDTO("Usuário com id "+ id +" deletado com sucesso!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<UserResponseDTO>> editUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(editUser.editUser(id , userRequestDTO));
    }
}
