package br.com.resenhasociocultural.apiresenha.model;

import br.com.resenhasociocultural.apiresenha.enums.AttendanceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "attendances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"youngster", "meeting"})
@ToString(exclude = {"youngster", "meeting"})
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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
