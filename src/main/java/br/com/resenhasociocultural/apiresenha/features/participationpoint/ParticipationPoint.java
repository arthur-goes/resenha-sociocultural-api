package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntry;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"youth", "meeting", "reason"})
@Getter
@Setter
@Table(name = "participation_points")
@Entity
@ToString(exclude = {"meeting"})
public class ParticipationPoint implements YouthEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "youth_id", nullable = false)
    private Youth youth;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = true)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = true)
    private Meeting meeting;

    @Column(nullable = false)
    private boolean active = true;
}
