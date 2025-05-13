package br.com.veras.room_reservation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroReserva;
    @Column(nullable = false)
    private LocalDateTime dataHora;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "login_user", referencedColumnName = "login"),
            @JoinColumn(name = "senha_user", referencedColumnName = "senha")
    })
    private User user;
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    @JsonBackReference
    private Sala sala;

    public Reserva() {
    }

    public Reserva(LocalDateTime dataHora, Sala sala, User user) {
        this.dataHora = dataHora;
        this.sala = sala;
        this.user = user;
    }

    public Reserva(LocalDateTime dataHora, Long id_sala, String login, String senha){
        this.dataHora = dataHora;
        sala.setId(id_sala);
        user.setUserId(new UserId(login,senha));
    }


}
