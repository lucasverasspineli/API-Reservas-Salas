package br.com.veras.room_reservation.controller;

import br.com.veras.room_reservation.dto.SalaDTO;
import br.com.veras.room_reservation.model.Sala;
import br.com.veras.room_reservation.service.impl.SalaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sala/")
public class SalaController {

    @Autowired
    private SalaServiceImpl salaServiceImpl;



    @GetMapping("/all")
    ResponseEntity<List<SalaDTO>> listAll(){
        List<SalaDTO> list = salaServiceImpl.todasSalas();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


    @ResponseBody
    @PostMapping()
    public ResponseEntity<SalaDTO> save (@RequestBody SalaDTO sala){
        SalaDTO novaSala = salaServiceImpl.criar(sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaSala);
    }

    @GetMapping("{id-sala}")
    public ResponseEntity<SalaDTO> search(@PathVariable(name = "id-sala") Long id){
        SalaDTO sala = salaServiceImpl.buscarSala(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(sala);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<SalaDTO> update(@PathVariable Long id, @RequestBody SalaDTO sala){
       SalaDTO novaSala = salaServiceImpl.editar(sala, id);
       return ResponseEntity.status(HttpStatus.OK).body(novaSala);
    }

    @DeleteMapping("del/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        salaServiceImpl.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body("Sala excluida com sucesso!");
    }

    @GetMapping("semReservas")
    public ResponseEntity<List<SalaDTO>> listaSemReserva(){
        List<SalaDTO> list = salaServiceImpl.semReservas();
        return ResponseEntity.status(HttpStatus.FOUND).body(list);
    }






}
