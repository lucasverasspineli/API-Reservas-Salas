package br.com.veras.room_reservation.repository;

import br.com.veras.room_reservation.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {


    List<Sala> findByStatusReservadoFalse();
    @Query("SELECT s FROM Sala s LEFT JOIN FETCH s.reservas WHERE s.id = :id")
    Optional<Sala> buscarSalaReservas(@Param("id") Long id);

}
