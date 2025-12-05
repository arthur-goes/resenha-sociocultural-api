package br.com.resenhasociocultural.apiresenha.features.userprofile;

import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileCreate;
import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileResponse;
import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileUpdate;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserProfileController {

    private UserProfileService userProfileService;
    private UserProfileMapper userProfileMapper;

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<UserProfile> createUser(@RequestBody @Valid UserProfileCreate dto){
        userProfileService.create(dto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserProfileResponse> adminUpdateUser(@PathVariable(name = "id") Long id, @RequestBody UserProfileUpdate dto) {
        UserProfileUpdate updateDto = dto.withId(id);
        UserProfile updatedUser = userProfileService.update(updateDto);
        UserProfileResponse responseDto = userProfileMapper.toDto(updatedUser);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserProfileResponse>> findUser(@RequestParam(name = "search", required = false) String searchText){
        List<UserProfile> users = userProfileService.findAllWithFilters(searchText);
        List<UserProfileResponse> response =  userProfileMapper.toResponseDtoList(users);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id){
        userProfileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
