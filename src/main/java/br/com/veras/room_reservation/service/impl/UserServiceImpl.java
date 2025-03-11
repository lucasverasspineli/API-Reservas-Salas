package br.com.veras.room_reservation.service.impl;

import br.com.veras.room_reservation.dto.UserDTO;
import br.com.veras.room_reservation.exception.NotFoundUserException;
import br.com.veras.room_reservation.exception.UserCredentialExistsException;
import br.com.veras.room_reservation.model.User;
import br.com.veras.room_reservation.model.UserId;
import br.com.veras.room_reservation.repository.UserRepository;
import br.com.veras.room_reservation.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO buscar(String login, String senha) {
        User user = userRepository.findById(new UserId(login, senha)).orElseThrow(NotFoundUserException::new);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO criar(UserDTO userDTO) {
        validationLoginPassword(userDTO.getLogin(), userDTO.getSenha());
        User user = new User();
        user.setUserId(new UserId(userDTO.getLogin(), userDTO.getSenha()));
        convertUserDtoForUser(userDTO, user);
        userRepository.save(user);
        return new UserDTO(user);
    }

    private void validationLoginPassword(String login, String senha) {
        Optional<User> opValid = userRepository.findById(new UserId(login, senha));
        if (opValid.isPresent())
            throw new UserCredentialExistsException();
    }

    private void convertUserDtoForUser(UserDTO userDTO, User user) {
        user.setUserId(((userDTO.getLogin() != user.getUserId().getLogin())
                || (userDTO.getSenha() != user.getUserId().getSenha())) ?
                new UserId(userDTO.getLogin(), userDTO.getSenha()) :
                new UserId(user.getUserId().getLogin(), user.getUserId().getSenha()));

        user.setNome((userDTO.getNome() == null) ? user.getNome() : userDTO.getNome());
        user.setSexo((userDTO.getSexo() == null) ? user.getSexo() : userDTO.getSexo());
        user.setCpf((userDTO.getCpf() == null) ? user.getCpf() : userDTO.getCpf());
        user.setPerfil((userDTO.getPerfil() == null) ? user.getPerfil() : userDTO.getPerfil());
        user.setInstituicao((userDTO.getInstituicao() == null) ? user.getInstituicao() : userDTO.getInstituicao());
    }

    @Transactional
    public UserDTO editar(UserDTO userDTO) {
        User editUser = userRepository.findById(new UserId(userDTO.getLogin(), userDTO.getSenha())).orElseThrow(() -> new NotFoundUserException());
        convertUserDtoForUser(userDTO, editUser);
        return new UserDTO(userRepository.save(editUser));
    }

    @Transactional
    public void excluir(String login, String senha) {
        User user = userRepository.findById(new UserId(login, senha)).orElseThrow(NotFoundUserException::new);
        userRepository.delete(user);
    }


}
