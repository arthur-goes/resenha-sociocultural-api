package br.com.resenhasociocultural.apiresenha.service;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthUpdateAdminDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.mapper.YouthMapper;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.repository.YouthRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

import static br.com.resenhasociocultural.apiresenha.builder.YouthBuilder.*;
import static br.com.resenhasociocultural.apiresenha.builder.YouthUpdateDtoBuilder.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class YouthServiceTest {

    @Mock
    YouthRepository youthRepository;

    @Mock
    YouthMapper youthMapper;

    @InjectMocks
    YouthService youthService;

    @Test
    void whenFindByIdWithNonExistentdId_shouldReturnResourceNotFoundException(){
        when(youthRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> youthService.findById(1L));
    }

    @Test
    void whenDeleteByIdWithNonExistentdId_shouldReturnResourceNotFoundException(){
        when(youthRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> youthService.deleteById(1L));
    }

    @Test
    void whenUpdateWithValidDto_shouldCallRepositoryAndMapper(){
        Youth foundYouth = aYouth().build();
        YouthUpdateAdminDto updateDto = aYouthUpdateDto().build();

        when(youthRepository.findById(anyLong())).thenReturn(Optional.of(aYouth().build()));

        youthService.update(updateDto);

        verify(youthRepository, times(1)).save(foundYouth);
        verify(youthMapper, times(1)).updateYouthFromDto(updateDto, foundYouth);
    }

}