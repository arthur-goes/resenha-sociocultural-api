package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.meeting.MeetingChildCollection;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attendances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"youth", "meeting"})
@ToString(exclude = {"youth", "meeting"})
public class Attendance implements MeetingChildCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id",nullable = false)
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "youngster_id", nullable = false)
    private Youth youth;

    @Enumerated(EnumType.STRING)
    @Column(name = "attendance_status", nullable = false)
    private AttendanceStatus attendanceStatus;

    @Column(name = "absence_excuse")
    private String absenceExcuse;
}
