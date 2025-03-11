package br.com.veras.room_reservation.dto;

import br.com.veras.room_reservation.model.User;
import jakarta.persistence.Column;

import java.util.Objects;

public class UserDTO {

    private String login;
    private String senha;

    private String nome;
    private String sexo;
    private String cpf;
    private String perfil;
    private String instituicao;


    public UserDTO() {
    }

    public UserDTO(User entity) {
        this.login = entity.getUserId().getLogin();
        this.senha = entity.getUserId().getSenha();
        this.cpf = entity.getCpf();
        this.instituicao = entity.getInstituicao();
        this.nome = entity.getNome();
        this.perfil = entity.getPerfil();
        this.sexo = entity.getSexo();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
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

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
