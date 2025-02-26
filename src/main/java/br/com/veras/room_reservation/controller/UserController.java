package br.com.veras.room_reservation.controller;

import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
import br.com.veras.room_reservation.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{login}/{senha}")
    public ResponseEntity<User> findByUser(@PathVariable String login, @PathVariable String senha){
        User user = userService.buscar(login,senha);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody User user){
        if(user.getUserId().getLogin() == null || user.getUserId().getSenha() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você tem que informar o Login e Senha");
        }
        User use = userService.criar(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(use);
    }

    @PutMapping("update-user")
    public ResponseEntity<User> update(@RequestBody User user){
       User usr = userService.editar(user);
       return ResponseEntity.status(HttpStatus.OK).body(usr);
    }

    @DeleteMapping("del/{login}/{senha}")
    public ResponseEntity<String> delete(@PathVariable String login, @PathVariable String senha){
        userService.excluir(login,senha);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário cancelado com sucesso!");
    }




}
