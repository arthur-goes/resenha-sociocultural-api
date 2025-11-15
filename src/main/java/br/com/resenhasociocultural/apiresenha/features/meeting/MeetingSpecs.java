package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MeetingSpecs {

    public Specification<Meeting> buildSpecificationsFromFilter(MeetingFilterDto filters){
        Specification<Meeting> specs = ((root, query, cb) -> cb.conjunction());

        boolean isDateBetweenFilterApplied = filters.initialDate() != null && filters.finalDate() != null;

        if (filters.date() != null){
            specs = specs.and(dateEqual(filters.date()));
        }

        if (filters.theme() != null){
            specs = specs.and(themeLike(filters.theme()));
        }

        if (!isDateBetweenFilterApplied){
            return specs;
        }

        return specs.and(dateBetween(filters.initialDate(), filters.finalDate()));

    }

    public  Specification<Meeting> dateEqual(LocalDate date){
        return (root, query, cb) -> cb.equal(root.get("date"), date);
    }

    public Specification<Meeting> dateBetween(LocalDate initialDate, LocalDate finalDate){
        return (root, query, cb) -> cb.between(root.get("date") ,initialDate, finalDate);
    }

    public Specification<Meeting> dateFrom(LocalDate date){
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("date"), date);
    }

    public Specification<Meeting> themeLike(String theme){
        return (root, query, cb) -> cb.like(cb.upper(root.get("theme")), "%" + theme.toUpperCase() + "%");
    }
}
