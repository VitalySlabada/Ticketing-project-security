package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import com.cydeo.enums.Gender;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ProjectServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @MockBean
    private TaskService taskService;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserService userService;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectMapper projectMapper;

    @InjectMocks
    ProjectServiceImpl projectService;

    @Test
    void getByProjectCode_test() {

        // Given
        Project project = new Project();
        ProjectDTO projectDTO = new ProjectDTO();

        when(projectRepository.findByProjectCode(anyString())).thenReturn(project);
        when(projectMapper.convertToDto(project)).thenReturn(projectDTO);

        // When
        ProjectDTO projectDTO1 = projectService.getByProjectCode(anyString());

        // Then
        verify(projectRepository).findByProjectCode(anyString());
        verify(projectMapper).convertToDto(any(Project.class));

        assertNotNull(projectDTO1);

    }

    /**
     * Method under test: {@link ProjectServiceImpl#getByProjectCode(String)}
     */
    @Test
    void testGetByProjectCode() {
        Role role = new Role();
        role.setDescription("The characteristics of someone or something");
        role.setId(123L);
        role.setInsertDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setInsertUserId(123L);
        role.setIsDeleted(true);
        role.setLastUpdateDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        role.setLastUpdateUserId(123L);

        User user = new User();
        user.setEnabled(true);
        user.setFirstName("Jane");
        user.setGender(Gender.MALE);
        user.setId(123L);
        user.setInsertDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setInsertUserId(123L);
        user.setIsDeleted(true);
        user.setLastName("Doe");
        user.setLastUpdateDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setLastUpdateUserId(123L);
        user.setPassWord("Pass Word");
        user.setPhone("4105551212");
        user.setRole(role);
        user.setUserName("janedoe");

        Project project = new Project();
        project.setAssignedManager(user);
        project.setEndDate(LocalDate.ofEpochDay(1L));
        project.setId(123L);
        project.setInsertDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        project.setInsertUserId(123L);
        project.setIsDeleted(true);
        project.setLastUpdateDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        project.setLastUpdateUserId(123L);
        project.setProjectCode("Project Code");
        project.setProjectDetail("Project Detail");
        project.setProjectName("Project Name");
        project.setProjectStatus(Status.OPEN);
        project.setStartDate(LocalDate.ofEpochDay(1L));
        when(projectRepository.findByProjectCode((String) any())).thenReturn(project);
        ProjectDTO projectDTO = new ProjectDTO();
        when(projectMapper.convertToDto((Project) any())).thenReturn(projectDTO);
        assertSame(projectDTO, projectServiceImpl.getByProjectCode("Code"));
        verify(projectRepository).findByProjectCode((String) any());
        verify(projectMapper).convertToDto((Project) any());
    }

    @Test
    void getByProjectCode_exception_test() {

        when(projectRepository.findByProjectCode("")).thenThrow(new RuntimeException("Project Not Found"));

        Throwable exception = assertThrows(RuntimeException.class, () -> projectService.getByProjectCode("PR01"));

//        verify(projectRepository).findByProjectCode(anyString());

        assertEquals("Project Not Found", exception.getMessage());

    }

    @Test
    void save_test() {

        ProjectDTO projectDTO = new ProjectDTO();
        Project project = new Project();

        when(projectMapper.convertToEntity(projectDTO)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);

        projectService.save(projectDTO);

        verify(projectRepository).save(project);
        verify(projectMapper).convertToEntity(any(ProjectDTO.class));

    }

}
