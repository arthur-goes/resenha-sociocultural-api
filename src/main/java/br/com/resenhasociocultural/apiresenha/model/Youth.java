package br.com.resenhasociocultural.apiresenha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
@Table(name = "youths")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Youth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    private String firstName;

    @Column(nullable = false)
    @NotBlank
    private String surname;

    @Column(nullable = false)
    @Past
    @NotNull
    private LocalDate birthDate;

    @Column(nullable = false, length = 11, unique = true)
    @NotBlank
    @CPF
    private String cpf;

    @Column(nullable = false)
    @NotBlank
    private String motherName;

    private String fatherName;

    @Column(name="emergency_contact_name", nullable = false)
    @NotBlank
    private String emergencyContactName;

    @Column(name = "emergency_contact_description", nullable = false)
    @NotBlank
    private String emergencyContactRelationship;

    @Column(name="emergency_contact_phone", nullable = false, length = 11)
    private String emergencyContactPhone;
}
