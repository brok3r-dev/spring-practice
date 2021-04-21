package com.spring.practice.service;

import com.spring.practice.entity.UserDetail;
import com.spring.practice.repository.UserDetailRepository;
import com.spring.practice.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceTest {
    @Mock
    UserDetailRepository userDetailRepository;

    UserDetailsService userDetailsService;

    String name = "test";
    UserDetail user = new UserDetail(name);

    @BeforeEach
    void setUp() {
        userDetailsService = new UserDetailsServiceImpl(userDetailRepository);
    }

    @Test
    void canLoadUserByUsername() {
        //given
        when(userDetailRepository.findByUsername(name)).thenReturn(user);

        //when
        userDetailsService.loadUserByUsername(name);

        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(userDetailRepository).findByUsername(name);
        verify(userDetailRepository).findByUsername(captor.capture());

        assertThat(captor.getValue()).isEqualTo(user.getUsername());
    }

    @Test
    void canNotLoadUserByUsername() {
        //given
        when(userDetailRepository.findByUsername(name)).thenReturn(null);

        //then
        assertThatThrownBy(() -> userDetailsService.loadUserByUsername(name))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}