package ro.ubb.SaloonApp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.SaloonApp.dto.CustomerViewDto;
import ro.ubb.SaloonApp.dto.LoginViewDto;
import ro.ubb.SaloonApp.dto.UserDto;
import ro.ubb.SaloonApp.dto.UserLoginDto;
import ro.ubb.SaloonApp.service.auth.AuthenticationService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<CustomerViewDto> register(@RequestBody @Valid UserDto userDto) {
        CustomerViewDto customerViewDto = authenticationService.register(userDto);
        return new ResponseEntity<>(customerViewDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginViewDto> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        LoginViewDto loginViewDto = authenticationService.login(userLoginDto);
        return new ResponseEntity<>(loginViewDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(name = "Authorization") String authHeader) {
        String response = authenticationService.logout(authHeader);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
