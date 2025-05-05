package br.com.resenhasociocultural.apiresenha.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "meetings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@EqualsAndHashCode(of = "id")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String theme;

    @OneToMany(mappedBy = "meeting")
    private Set<Attendance> presenceList;

    @OneToMany(mappedBy = "meeting")
    private Set<Strike> strikes;
}
