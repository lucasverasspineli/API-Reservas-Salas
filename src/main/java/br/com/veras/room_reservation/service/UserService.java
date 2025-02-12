package br.com.veras.room_reservation.service;

import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
import br.com.veras.room_reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(String login, String senha){
      Optional<User> opUser= userRepository.findById(new UserId(login,senha));
      if(!opUser.isPresent()){
          throw new RuntimeException("Não existe este usuário");
      }
      return opUser.get();
    }



}
