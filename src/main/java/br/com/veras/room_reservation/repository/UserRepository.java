package br.com.veras.room_reservation.repository;

import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {


}
