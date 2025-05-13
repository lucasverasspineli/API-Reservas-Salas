package br.com.veras.room_reservation.service.interfaces;
import br.com.veras.room_reservation.dto.UserDTO;

public interface UserService {


    public UserDTO criar(UserDTO userDTO, String senha);
    public UserDTO editar(UserDTO userDTO, String senha);
    public void excluir(String login, String senha);
    public UserDTO buscar(String login, String Senha);

}
