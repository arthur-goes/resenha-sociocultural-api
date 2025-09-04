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

    public Specification<AttendanceEntry> youthNameOrSurnameLike(String youthName){
        return (root, query, cb) -> {
            Join<AttendanceEntry, Youth> youthJoin = root.join("youth");
            Predicate nameLike = cb.like(cb.upper(youthJoin.get("firstName")), "%" + youthName.toUpperCase() + "%");
            Predicate surnameLike = cb.like(cb.upper(youthJoin.get("surname")), "%" + youthName.toUpperCase() + "%");
            return cb.or(nameLike, surnameLike);
        };
    }

    public Specification<AttendanceEntry> dateEqual(LocalDate date) {
        return (root, query, cb) -> {
            Join<AttendanceEntry, Meeting> join = root.join("meeting");
            return cb.equal(join.get("date"), date);
        };
    }

    public Specification<AttendanceEntry> dateBetween(LocalDate initialDate, LocalDate finalDate) {
        return (root, query, cb) -> {
            Join<AttendanceEntry, Meeting> join = root.join("meeting");
            return cb.between(join.get("date"), initialDate, finalDate);
        };
    }
}
