package br.com.veras.room_reservation.controller;

import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
import br.com.veras.room_reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("{login}/{senha}")
    public ResponseEntity<User> findByUser(@PathVariable String login, @PathVariable String senha){
        User user = userService.findById(login,senha);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
