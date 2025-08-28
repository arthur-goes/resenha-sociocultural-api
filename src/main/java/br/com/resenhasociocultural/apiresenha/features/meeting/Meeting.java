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

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Attendance> attendanceList = new HashSet<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Strike> strikes = new HashSet<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ParticipationPoint> participationPoints = new HashSet<>();

    public void addAttendance(Attendance attendance){
        attendance.setMeeting(this);
        attendanceList.add(attendance);
    }

    public void addStrike(Strike strike){
        strike.setMeeting(this);
        strikes.add(strike);
    }

    public void addParticipationPoint(ParticipationPoint participation){
        participation.setMeeting(this);
        participationPoints.add(participation);
    }
}
