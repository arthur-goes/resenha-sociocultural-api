package br.com.resenhasociocultural.apiresenha.model;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "youth_id", nullable = false)
    private Youth youth;

    @Column(nullable = true)
    private int amount;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = true)
    private Meeting meeting;

    @Column(nullable = false)
    private boolean active = true;
}
