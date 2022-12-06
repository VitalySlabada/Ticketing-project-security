package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskServiceImpl taskService;

    @ParameterizedTest
    @ValueSource(longs = {1L, 2L, 3L, -5L})
    void findById_test(long id) {

        // Given
        Task task = new Task();

        when(taskRepository.findById(id)).thenReturn(Optional.of(task));
        when(taskMapper.convertToDTO(task)).thenReturn(new TaskDTO());

        // When
        taskService.findById(id);

        // Then
        verify(taskRepository).findById(id);
        verify(taskRepository).findById(anyLong());

        verify(taskMapper).convertToDTO(any(Task.class));
        verify(taskMapper).convertToDTO(task);

//        verify(taskRepository, never()).findById(-5L);

    }

    @Test
    void findById_bdd_test() {

        // Given
        Task task = new Task();
        given(taskRepository.findById(anyLong())).willReturn(Optional.of(task));
        given(taskMapper.convertToDTO(task)).willReturn(new TaskDTO());

        // When
        taskService.findById(anyLong());

        // Then
        then(taskRepository).should().findById(anyLong());
        then(taskRepository).should(never()).findById(-5L);

    }

}
