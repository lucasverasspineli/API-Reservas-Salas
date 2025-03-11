package br.com.veras.room_reservation.service.interfaces;
import br.com.veras.room_reservation.dto.UserDTO;

import java.util.List;

public interface UserService {


    public UserDTO criar(UserDTO userDTO);
    public UserDTO editar(UserDTO userDTO);
    public void excluir(String login, String senha);
    public UserDTO buscar(String login, String Senha);

}
