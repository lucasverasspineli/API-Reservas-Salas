package br.com.veras.room_reservation.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
public class User {

    @EmbeddedId
    private UserId userId;

    @Column(nullable = false)
    private String nome;
    private String sexo;
    @Column(unique = true)
    private String cpf;
    @Column(nullable = false)
    private String perfil;
    private String instituicao;


    public User(String login, String senha, String cpf, String instituicao, String nome, String perfil, String sexo) {
        this.userId = new UserId(login, senha);
        this.cpf = cpf;
        this.instituicao = instituicao;
        this.nome = nome;
        this.perfil = perfil;
        this.sexo = sexo;
    }
}
