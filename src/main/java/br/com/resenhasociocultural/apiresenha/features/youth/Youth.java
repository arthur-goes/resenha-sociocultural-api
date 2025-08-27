package br.com.resenhasociocultural.apiresenha.features.youth;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "youths")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor()
@EqualsAndHashCode(of = "id")
public class Youth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(nullable = true)
    private String surname;

    @Column(nullable = true)
    private LocalDate birthDate;

    @Column(nullable = true, length = 11, unique = true)
    private String cpf;

    @Column(nullable = true)
    private String motherName;

    @Column(nullable = true)
    private String fatherName;

    @Column(name="emergency_contact_name", nullable = true)
    private String emergencyContactName;

    @Column(name = "emergency_contact_description", nullable = true)
    private String emergencyContactRelationship;

    @Column(name="emergency_contact_phone", nullable = true, length = 11)
    private String emergencyContactPhone;

    @Column(name="creation_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDate creationDate;

    @Column(nullable = false)
    private boolean active;
}
