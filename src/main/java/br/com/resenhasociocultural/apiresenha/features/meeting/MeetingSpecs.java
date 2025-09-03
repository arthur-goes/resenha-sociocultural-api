package br.com.resenhasociocultural.apiresenha.features.meeting;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MeetingSpecs {
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
