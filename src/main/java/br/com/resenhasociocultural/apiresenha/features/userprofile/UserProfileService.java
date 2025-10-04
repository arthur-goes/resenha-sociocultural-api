package br.com.resenhasociocultural.apiresenha.features.userprofile;

import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileCreateDto;
import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserProfileService {
    private UserProfileRepository userProfileRepository;
    private UserProfileMapper userProfileMapper;
    private UserProfileSpecs userProfileSpecs;
    private PasswordEncoder encoder;

    public UserProfile findById(Long id) {
        return userProfileRepository
            .findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("Não foi possível encontrar um cadastro de usuário com id " + id)
            );
    }

    public void create(UserProfileCreateDto dto){
        UserProfile user = userProfileMapper.toEntity(dto);
        String encodedPassword = encoder.encode(dto.password());
        System.out.println("Encoded password: " + encodedPassword);
        user.setPassword(encodedPassword);
        userProfileRepository.save(user);
    }

    public UserProfile update(UserProfileUpdateDto dto){
        Optional<UserProfile> userProfile = userProfileRepository.findById(dto.id());
        if (userProfile.isEmpty()){
            throw new ResourceNotFoundException("Erro ao tentar atualizar os dados do usuário. Usuário inexistente.");
        }
        UserProfile user = userProfile.get();
        System.out.println("Password db: " + user.getPassword());
        encoder.matches(null, user.getPassword());
        userProfileMapper.toUpdatedEntity(dto, user);

        if (dto.password() != null && !encoder.matches(dto.password(), user.getPassword())){
            user.setPassword(
                encoder.encode(dto.password())
            );
        }

        return userProfileRepository.save(user);
    }

    public List<UserProfile> findAllWithFilters(String searchText){
        Specification<UserProfile> specification = userProfileSpecs.containing(searchText);
        return userProfileRepository.findAll(specification);
    }

    public void deleteById(Long id){
        userProfileRepository.deleteById(id);
    }
}
