package br.com.veras.room_reservation.controller;

import br.com.veras.room_reservation.dto.UserDTO;
import br.com.veras.room_reservation.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("{login}/{senha}")
    public ResponseEntity<UserDTO> findByUser(@PathVariable String login, @PathVariable String senha){
        UserDTO userDTO = userService.buscar(login,senha);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PostMapping("create/{senha}")
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO userDTO, @PathVariable String senha){
        if(userDTO.getLogin() == null || senha == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Você tem que informar o Login e Senha");
        }
        UserDTO user =userService.criar(userDTO,senha);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("update/{login}/{senha}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO,@PathVariable String login ,@PathVariable String senha){
       UserDTO user = userService.editar(userDTO ,senha);
       return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("del/{login}/{senha}")
    public ResponseEntity<String> delete(@PathVariable String login, @PathVariable String senha){
        userService.excluir(login,senha);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário cancelado com sucesso!");
    }

}
