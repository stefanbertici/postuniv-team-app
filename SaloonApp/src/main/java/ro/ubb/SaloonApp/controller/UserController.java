package ro.ubb.SaloonApp.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.SaloonApp.dto.UserViewDto;
import ro.ubb.SaloonApp.service.UserService;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserViewDto>> readAllUsers() {
        List<UserViewDto> users = userService.readAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserViewDto> readUserById(@PathVariable Integer id) {
        UserViewDto user = userService.readUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
