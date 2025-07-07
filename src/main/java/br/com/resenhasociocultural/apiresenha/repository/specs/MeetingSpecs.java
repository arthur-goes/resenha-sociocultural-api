package br.com.resenhasociocultural.apiresenha.repository.specs;

import br.com.resenhasociocultural.apiresenha.model.Meeting;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MeetingSpecs {
    public  static Specification<Meeting> dateEqual(LocalDate date){
        return (root, query, cb) -> cb.equal(root.get("date"), date);
    }

    public static Specification<Meeting> dateBetween(LocalDate initialDate, LocalDate finalDate){
        return (root, query, cb) -> cb.between(root.get("date") ,initialDate, finalDate);
    }

    public static Specification<Meeting> dateFrom(LocalDate date){
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("date"), date);
    }

    public static Specification<Meeting> themeLike(String theme){
        return (root, query, cb) -> cb.like(cb.upper(root.get("theme")), "%" + theme.toUpperCase() + "%");
    }
}
