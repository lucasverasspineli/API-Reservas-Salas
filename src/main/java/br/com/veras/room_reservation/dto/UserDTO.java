package br.com.veras.room_reservation.dto;

import br.com.veras.room_reservation.model.Reserva;
import br.com.veras.room_reservation.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDTO {

    private String login;
    private String nome;
    private String sexo;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cpf;
    private String perfil;
    private String instituicao;
    private List<ReservaMinDTO> reservas = new ArrayList<>();

    public UserDTO() {
    }

    public UserDTO(User entity) {
        this.login = entity.getUserId().getLogin();
        this.nome = entity.getNome();
        this.sexo = entity.getSexo();
        this.cpf = entity.getCpf();
        this.instituicao = entity.getInstituicao();
        this.perfil = entity.getPerfil();
        if(entity.getReservas() != null){
            this.reservas = entity.getReservas()
                    .stream()
                    .map(ReservaMinDTO::new)
                    .collect(Collectors.toList());
        }
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<ReservaMinDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaMinDTO> reservas) {
        this.reservas = reservas;
    }
}
