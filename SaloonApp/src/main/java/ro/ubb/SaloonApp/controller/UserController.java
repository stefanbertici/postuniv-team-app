package ro.ubb.SaloonApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.ubb.SaloonApp.dto.EmployeeViewDto;
import ro.ubb.SaloonApp.service.UserService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<EmployeeViewDto>> readAll() {
        List<EmployeeViewDto> users = userService.readAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeViewDto> readById(@PathVariable Integer id) {
        EmployeeViewDto user = userService.readById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/change-password")
    public ResponseEntity<String> changePassword(@PathVariable Integer id, @RequestBody String password, Authentication authentication) {
        String response = userService.changePassword(id, password, authentication);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/availability/{date}")
    public ResponseEntity<List<LocalTime>> getAvailability(@PathVariable @NonNull Integer id, @PathVariable @NonNull LocalDate date) {
        List<LocalTime> availability = userService.getAvailability(id, date);
        return new ResponseEntity<>(availability, HttpStatus.OK);
    }
}
