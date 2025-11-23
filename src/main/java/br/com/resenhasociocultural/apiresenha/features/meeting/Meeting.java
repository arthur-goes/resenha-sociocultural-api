package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.features.strike.Strike;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "meetings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String theme;

    @Column(nullable = false)
    private String minutosDeSabedoriaLesson;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Attendance> attendanceEntries = new HashSet<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Strike> strikeEntries = new HashSet<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ParticipationPoint> participationPointEntries = new HashSet<>();

    public void addAttendances(Attendance attendance){
        attendance.setMeeting(this);
        attendanceEntries.add(attendance);
    }

    public void addStrikes(Strike strike){
        strike.setMeeting(this);
        strikeEntries.add(strike);
    }

    public void addParticipationPoints(ParticipationPoint participation){
        participation.setMeeting(this);
        participationPointEntries.add(participation);
    }
}
