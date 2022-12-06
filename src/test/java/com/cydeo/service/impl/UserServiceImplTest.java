package com.cydeo.service.impl;

import com.cydeo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class})
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void deleteByUsername_test() {

        userService.deleteByUserName("manager@manager.com");

        verify(userRepository).deleteByUserName("manager@manager.com");
//        verify(userRepository, times(2)).deleteByUserName("manager@manager.com");
//        verify(userRepository, atLeastOnce()).deleteByUserName("manager@manager.com");
//        verify(userRepository, atLeast(5)).deleteByUserName("manager@manager.com");
//        verify(userRepository, atMostOnce()).deleteByUserName("manager@manager.com");
//        verify(userRepository, atMost(5)).deleteByUserName("manager@manager.com");

        InOrder inOrder = inOrder(userRepository);

        inOrder.verify(userRepository).deleteByUserName("manager@manager.com");
//        inOrder.verify(userRepository).findAll();

    }

}