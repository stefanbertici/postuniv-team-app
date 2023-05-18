package ro.ubb.SaloonApp.controller;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.SaloonApp.dto.CustomerViewDto;
import ro.ubb.SaloonApp.dto.LoginViewDto;
import ro.ubb.SaloonApp.dto.UserDto;
import ro.ubb.SaloonApp.dto.UserLoginDto;
import ro.ubb.SaloonApp.service.auth.AuthenticationService;


@AllArgsConstructor
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

    /*
        This endpoint is temporary only.
        Spring security redirects unauthorized requests to this, so we can send an error message to the user.
        This will be removed once e have a proper login page to which spring security can redirect.
     */
    @Hidden
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return new ResponseEntity<>("You have to log in first. Send a POST request with 'email' and 'password' fields to '/auth/login'.", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(name = "Authorization") String authHeader) {
        String response = authenticationService.logout(authHeader);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
