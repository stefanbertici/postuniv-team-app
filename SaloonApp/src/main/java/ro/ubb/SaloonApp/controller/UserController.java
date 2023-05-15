package ro.ubb.SaloonApp.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.SaloonApp.dto.UserDto;
import ro.ubb.SaloonApp.dto.UserViewDto;
import ro.ubb.SaloonApp.service.UserService;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserViewDto> createUser(@RequestBody @Valid UserDto userDto) {
        UserViewDto user = userService.registerUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
