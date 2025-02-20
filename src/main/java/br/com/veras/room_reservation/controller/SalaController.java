package br.com.veras.room_reservation.controller;

import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sala/")
public class SalaController {

    @Autowired
    private SalaService salaService;



    @GetMapping("/all")
    ResponseEntity<List<Sala>> listAll(){
        List<Sala> list = salaService.listagem();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


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

    @PutMapping("editar/{id}")
    public ResponseEntity<Sala> update(@PathVariable Long id, @RequestBody Sala sala){
       Sala novaSala = salaService.editar(id, sala);
       return ResponseEntity.status(HttpStatus.OK).body(novaSala);
    }

    @DeleteMapping("del/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        salaService.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body("Sala excluida com sucesso!");
    }

    @GetMapping("semReservas")
    public ResponseEntity<List<Sala>> listaSemReserva(){
        List<Sala> list = salaService.semReservas();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }






}
