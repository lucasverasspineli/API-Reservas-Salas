package br.com.veras.room_reservation.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.ColumnDefault;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private Integer capacidade;
    @Column(nullable = false)
    private String recursos;
    @Column(nullable = false)
    private boolean statusReservado;

}
