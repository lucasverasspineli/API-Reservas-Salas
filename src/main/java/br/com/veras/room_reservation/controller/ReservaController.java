package br.com.veras.room_reservation.controller;

import br.com.veras.room_reservation.dto.ReservaDTO;
import br.com.veras.room_reservation.service.impl.ReservaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reserva/")
public class ReservaController {

    @Autowired
    private ReservaServiceImpl reservaServiceImpl;

    @PostMapping("{login}/{senha}/{id_sala}")
    public ResponseEntity<ReservaDTO> create(@PathVariable String login, @PathVariable String senha, @PathVariable Long id_sala){
       ReservaDTO reserva = reservaServiceImpl.criar(login,senha,id_sala);
        return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
    }

}
