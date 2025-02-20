package br.com.veras.room_reservation.repository;

import br.com.veras.room_reservation.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {


    List<Sala> findByStatusReservadoFalse();

}
