package br.com.resenhasociocultural.apiresenha.features.userprofile;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserProfileSpecs {
    public Specification<UserProfile> containing(String searchText){
        return ((root, query, cb) -> {
            String[] substringSearchList = searchText.split(" ");
            return Arrays.stream(substringSearchList)
                .map(substring -> {
                    String pattern = "%" + substring.toUpperCase() + "%";

                    Predicate usernameLike = cb.like(cb.upper(root.get("username")), pattern );
                    Predicate firstNameLike = cb.like(cb.upper(root.get("firstName")), pattern );
                    Predicate surnameLike = cb.like(cb.upper(root.get("surname")), pattern );
                    return cb.or(usernameLike, firstNameLike, surnameLike);
                })
                .reduce(cb.disjunction(), cb::or);
        });
    }
}

