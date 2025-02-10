package br.com.veras.room_reservation.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserId implements Serializable {

    private String login;
    private String senha;


    public UserId() {
    }

    public UserId(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserId userId)) return false;
        return Objects.equals(login, userId.login) && Objects.equals(senha, userId.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, senha);
    }

}
