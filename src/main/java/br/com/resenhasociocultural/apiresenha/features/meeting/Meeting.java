package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceEntry;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPointEntry;
import br.com.resenhasociocultural.apiresenha.features.strike.StrikeEntry;
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
    private Set<AttendanceEntry> attendanceEntries = new HashSet<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<StrikeEntry> strikeEntries = new HashSet<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ParticipationPointEntry> participationPointEntries = new HashSet<>();

    public void addAttendanceEntries(AttendanceEntry attendance){
        attendance.setMeeting(this);
        attendanceEntries.add(attendance);
    }

    public void addStrikeEntries(StrikeEntry strike){
        strike.setMeeting(this);
        strikeEntries.add(strike);
    }

    public void addParticipationPointEntries(ParticipationPointEntry participation){
        participation.setMeeting(this);
        participationPointEntries.add(participation);
    }
}
