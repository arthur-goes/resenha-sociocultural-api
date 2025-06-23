package br.com.resenhasociocultural.apiresenha.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "strikes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Strike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "youth_id", nullable = false)
    private Youth youth;

    @Column(nullable = true)
    private int amount;

    @Column(nullable = false, length = 256)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = true)
    private Meeting meeting;

    @Column(nullable = false)
    private boolean active = true;
}
