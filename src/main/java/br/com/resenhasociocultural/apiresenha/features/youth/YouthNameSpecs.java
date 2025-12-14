package br.com.resenhasociocultural.apiresenha.features.youth;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class YouthNameSpecs {
    private <Entity> Specification<Entity> nameOrSurnameLike(String youthNameSubstring, Function<Root<Entity>,Path<Youth>> pathGenerator){
        return (root, query, cb) -> {
            Path<Youth> youthPath = pathGenerator.apply(root);
            Predicate nameLike = cb.like(cb.upper(youthPath.get("firstName")), "%" + youthNameSubstring.toUpperCase() + "%");
            Predicate surnameLike = cb.like(cb.upper(youthPath.get("surname")), "%" + youthNameSubstring.toUpperCase() + "%");
            return cb.or(nameLike, surnameLike);
        };
    }

    public Specification<Youth> nameOrSurnameLikeForYouth(String nameSubstring){
        return nameOrSurnameLike(nameSubstring, root -> root);
    }

    public <E extends YouthEntry> Specification<E> nameOrSurnameLikeForYouthEntry(String nameSubstring){
        return nameOrSurnameLike(nameSubstring, root -> root.join("youth"));
    }
}
