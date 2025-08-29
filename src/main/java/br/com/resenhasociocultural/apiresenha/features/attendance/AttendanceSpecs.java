package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AttendanceSpecs {

    public static Specification<Attendance> youthNameOrSurnameLike(String youthName){
        return (root, query, cb) -> {
            Join<Attendance, Youth> youthJoin = root.join("youth");
            Predicate nameLike = cb.like(cb.upper(youthJoin.get("name")), "%" + youthName.toUpperCase() + "%");
            Predicate surnameLike = cb.like(cb.upper(youthJoin.get("surname")), "%" + youthName.toUpperCase() + "%");
            return cb.or(nameLike, surnameLike);
        };
    }

    public static Specification<Attendance> dateEqual(LocalDate date) {
        return (root, query, cb) -> {
            Join<Attendance, Meeting> join = root.join("meeting");
            return cb.equal(join.get("date"), date);
        };
    }

    public static Specification<Attendance> dateBetween(LocalDate initialDate, LocalDate finalDate) {
        return (root, query, cb) -> {
            Join<Attendance, Meeting> join = root.join("meeting");
            return cb.between(join.get("date"), initialDate, finalDate);
        };
    }
}
