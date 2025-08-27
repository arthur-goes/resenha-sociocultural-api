package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Table(name = "participation_points")
@Entity
public class ParticipationPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "youth_id", nullable = false)
    private Youth youth;

    @Column(nullable = true)
    private int amount;

    @Column(nullable = true)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = true)
    private Meeting meeting;

    @Column(nullable = false)
    private boolean active = true;
}
