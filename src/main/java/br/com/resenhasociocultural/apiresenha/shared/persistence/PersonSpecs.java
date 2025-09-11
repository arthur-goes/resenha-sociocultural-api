package br.com.resenhasociocultural.apiresenha.shared.persistence;

import br.com.resenhasociocultural.apiresenha.shared.domain.Person;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public interface PersonSpecs<P extends Person> {
    default Specification<P> nameOrSurnameLike(String searchTerm){
        return ((root, query, cb) -> {
            cb.conjunction();
            Predicate namelike = likePredicate(searchTerm, root.get("firstName"), cb);
            Predicate surnamelike = likePredicate(searchTerm, root.get("surname"), cb);
            return cb.or(namelike, surnamelike);
        });
    }

    default Predicate likePredicate(String searchTerm, Path<String> path, CriteriaBuilder cb){
        if (cb instanceof Predicate){
            cb.conjunction();
        }
        return cb.like(cb.upper(path), "%" + searchTerm.toUpperCase() + "%");
    }
}