package br.com.veras.room_reservation.controller;

import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sala/")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @ResponseBody
    @PostMapping()
    public ResponseEntity<Sala> save (@RequestBody Sala sala){
        Sala novaSala = salaService.criarSala(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSala);
    }

    @GetMapping("{id-sala}")
    public ResponseEntity<Sala> search(@PathVariable(name = "id-sala") Long id){
        Sala sala = salaService.buscarSala(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(sala);
    }


}
