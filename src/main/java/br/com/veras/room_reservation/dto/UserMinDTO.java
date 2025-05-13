package br.com.veras.room_reservation.dto;

import br.com.veras.room_reservation.model.User;

public class UserMinDTO {

    private String login;
    private String nome;
    private String sexo;

    public UserMinDTO(){}

    public UserMinDTO(User entity){
        login = entity.getUserId().getLogin();
        nome = entity.getNome();
        sexo = entity.getSexo();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
