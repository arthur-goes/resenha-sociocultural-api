package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceFilter;
import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthNameSpecs;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor
@Component
public class AttendanceSpecs {

    private final YouthNameSpecs youthNameSpecs;

    public Specification<Attendance> buildSpecificationsFromFilters(AttendanceFilter filters){
        boolean isDateBetweenApplied = filters.initialDate() != null && filters.finalDate() != null;
        boolean isDateBetweenIntervalNotInverted = isDateBetweenApplied && (filters.initialDate().isBefore(filters.finalDate()));

        Specification<Attendance> specifications = fetchYouth();

        if (filters.youthNameSubstring() != null){
            specifications = specifications.and(youthNameSpecs.nameOrSurnameLikeForYouthEntry(filters.youthNameSubstring()));
        }

        if (filters.date() != null){
            specifications = specifications.and(dateEqual(filters.date()));
        }

        if (!isDateBetweenApplied){
            return specifications;
        }

        if (isDateBetweenIntervalNotInverted) {
            return specifications = specifications.and(dateBetween(filters.initialDate(), filters.finalDate()));
        }
        specifications = specifications.and(dateBetween(filters.finalDate(), filters.initialDate()));

        return specifications;
    }

    public Specification<Attendance> dateEqual(LocalDate date) {
        return (root, query, cb) -> {
            Join<Attendance, Meeting> join = root.join("meeting");
            return cb.equal(join.get("date"), date);
        };
    }

    public Specification<Attendance> dateBetween(LocalDate initialDate, LocalDate finalDate) {
        return (root, query, cb) -> {
            Join<Attendance, Meeting> join = root.join("meeting");
            return cb.between(join.get("date"), initialDate, finalDate);
        };
    }

    public Specification<Attendance> fetchYouth(){
        return ((root, query, cb) -> {
            root.fetch("youth", JoinType.LEFT);
            return cb.conjunction();
        });
    }
}
