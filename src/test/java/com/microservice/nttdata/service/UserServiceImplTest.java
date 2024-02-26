package com.microservice.nttdata.service;

import com.microservice.nttdata.dto.SignUpUserDto;
import com.microservice.nttdata.dto.SignUpUserPhoneDto;
import com.microservice.nttdata.entity.User;
import com.microservice.nttdata.entity.UserPhone;
import com.microservice.nttdata.repository.UserRepository;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_ValidUser_ReturnsSavedUser() {
        SignUpUserDto signUpUserDto = new SignUpUserDto();
        signUpUserDto.setName("John Doe");
        signUpUserDto.setEmail("john.doe@example.com");
        signUpUserDto.setPassword("password");
        signUpUserDto.setPhones(Collections.singletonList(new SignUpUserPhoneDto(123L, 4, "5")));

        User expectedUser = new User("John Doe", "john.doe@example.com", "password", Collections.singletonList(new UserPhone(123L, 4, "5")));
        expectedUser.setCreated(new Date());
        expectedUser.setModified(new Date());
        expectedUser.setLastLogin(new Date());
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        User savedUser = userService.createUser(signUpUserDto);

        assertEquals(expectedUser.getName(), savedUser.getName());
        assertEquals(expectedUser.getEmail(), savedUser.getEmail());
    }

    @Test
    void getUserByName_ExistingUser_ReturnsOptionalWithUser() {
        String userEmail = "john.doe@example.com";
        User expectedUser = new User("John Doe", "john.doe@example.com", "password", Collections.singletonList(new UserPhone(123L, 4, "5")));
        expectedUser.setLastLogin(new Date());

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(expectedUser));

        Optional<User> userOptional = userService.getUserByEmail(userEmail);

        assertEquals(expectedUser, userOptional.orElseThrow());
    }

    @Test
    void getUserByName_NonExistingUser_ReturnsEmptyOptional() {
        String nonExistingUsername = "NonExistingUser";
        when(userRepository.findByEmail(nonExistingUsername)).thenReturn(Optional.empty());

        Optional<User> userOptional = userService.getUserByEmail(nonExistingUsername);

        assertEquals(Optional.empty(), userOptional);
    }

    @Test
    void updateLastLogin_ExistingUser_ReturnsExistingUserUpdated() {
        User requestUser = new User( "John Doe", "john.doe@example.com", "password", Collections.singletonList(new UserPhone(123L, 4, "5")));
        User expectedUser = new User( "John Doe", "john.doe@example.com", "password", Collections.singletonList(new UserPhone(123L, 4, "5")));
        requestUser.setModified(new Date());
        requestUser.setLastLogin(new Date());
        expectedUser.setModified(new Date());
        expectedUser.setLastLogin(new Date());

        when(userRepository.save(requestUser)).thenReturn(expectedUser);

        User resultUser = userService.updateLastLogin(requestUser);

        assertEquals(expectedUser.getLastLogin(), resultUser.getLastLogin());
    }

}
