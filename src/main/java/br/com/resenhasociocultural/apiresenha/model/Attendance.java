package br.com.resenhasociocultural.apiresenha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotNull
    private Youth youth;

    @Enumerated(EnumType.STRING)
    @Column(name = "attendance_status", nullable = false)
    @NotNull
    private AttendanceStatus attendanceStatus;

    @Column(name = "absence_excuse")
    private String absenceExcuse;
}
