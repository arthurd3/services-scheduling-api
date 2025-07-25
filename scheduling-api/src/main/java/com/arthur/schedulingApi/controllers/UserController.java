package com.arthur.schedulingApi.controllers;

import com.arthur.schedulingApi.controllers.request.UserRequestDTO;
import com.arthur.schedulingApi.controllers.response.UserResponseDTO;
import com.arthur.schedulingApi.usecases.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final FindAllUsers findAllUsers;
    private final CreateUser createUser;
    private final FindUser findUser;
    private final DeleteUser deleteUser;
    private final EditUser editUser;


    @ResponseStatus(FOUND)
    @GetMapping("findAll")
    public Page<UserResponseDTO> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "size", defaultValue = "5") int size) {
        return findAllUsers.findAllUsers(PageRequest.of(page, size));
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public UserResponseDTO create(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return createUser.registerUser(userRequestDTO);
    }

    @ResponseStatus(FOUND)
    @GetMapping("{id}")
    public UserResponseDTO findById(@PathVariable Long id ) {
        return findUser.findById(id);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        deleteUser.deleteUser(id);
    }

    @ResponseStatus(OK)
    @PutMapping("{id}")
    public UserResponseDTO update(@PathVariable Long id,
                                  @Valid @RequestBody UserRequestDTO userRequestDTO) {
        return editUser.editUser(id , userRequestDTO);
    }

}
