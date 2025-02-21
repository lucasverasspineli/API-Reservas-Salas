package br.com.veras.room_reservation.service;

import br.com.veras.room_reservation.exception.NotFoundUserException;
import br.com.veras.room_reservation.exception.UserCredentialExistsException;
import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
import br.com.veras.room_reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findById(String login, String senha){
      Optional<User> opUser= userRepository.findById(new UserId(login,senha));
      if(!opUser.isPresent()){
          throw new NotFoundUserException();
      }
      return opUser.get();
    }

    public User criarUsuario(User user){
        Optional<User> opUser = userRepository.findById(user.getUserId());
        if(opUser.isPresent()){
            throw new UserCredentialExistsException();
        }
        return userRepository.save(user);
    }



}
