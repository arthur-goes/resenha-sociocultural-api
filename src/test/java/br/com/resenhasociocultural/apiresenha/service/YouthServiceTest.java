package br.com.resenhasociocultural.apiresenha.service;

import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.repository.YouthRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class YouthServiceTest {

    @Mock
    YouthRepository youthRepository;

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

    void whenUpdateWithValidDto_shouldMapDtoAndSave(){
        when(youthRepository.findById(anyLong())).thenReturn(Optional.of(new Youth()));
    }

}
