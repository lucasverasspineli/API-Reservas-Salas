package br.com.veras.room_reservation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
public class User {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "login", column = @Column(name = "login", nullable = false)),
            @AttributeOverride(name = "senha", column = @Column(name = "senha", nullable = false))
    })
    private UserId userId;

    @Column(nullable = false)
    private String nome;
    private String sexo;
    @Column(unique = true)
    private String cpf;
    @Column(nullable = false)
    private String perfil;
    private String instituicao;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Reserva> reservas;

    public User(){}

    public User(String login, String senha, String cpf, String instituicao, String nome, String perfil, String sexo) {
        this.userId = new UserId(login, senha);
        this.cpf = cpf;
        this.instituicao = instituicao;
        this.nome = nome;
        this.perfil = perfil;
        this.sexo = sexo;
    }

}
