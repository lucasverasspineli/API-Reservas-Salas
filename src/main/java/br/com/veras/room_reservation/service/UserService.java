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

    public User editar(User user){
        User buscar  = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
        buscar.setNome(user.getNome());
        buscar.setSexo(user.getSexo());
        buscar.setCpf(user.getCpf());
        buscar.setPerfil(user.getPerfil());
        buscar.setInstituicao(user.getInstituicao());
        return userRepository.save(buscar);
    }

    public void excluir(String login, String senha){
        Optional<User> userDel = userRepository.findById(new UserId(login,senha));
        if(!userDel.isPresent()){
            throw new NotFoundUserException("Esse Usuário não existe");
        }
        userRepository.delete(userDel.get());
    }


}
