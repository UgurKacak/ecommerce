package com.ugur.service.impl;

import com.ugur.converter.UserConverter;
import com.ugur.dto.UserDto;
import com.ugur.entity.User;
import com.ugur.exception.BusinessException;
import com.ugur.repository.UserRepository;
import com.ugur.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.ValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConverter userConverter;

    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @BeforeEach
    public void setup() {
        userService = new UserServiceImpl(userRepository, userConverter);
    }

    @Test
    @DisplayName("Test should pass when userDto object is null.")
    void shouldValidateUser() {
        assertThatThrownBy(() -> {
            userService.validateUser(null);
        }).isInstanceOf(ValidationException.class)
                .hasMessage("User object is null.");
    }

    @Test
    @DisplayName("Test should pass when userDto object has email attribute.")
    void shouldHaveEmailAttribute() {
        UserDto userDto = UserDto.builder().build();

        assertThatThrownBy(() -> {
            userService.validateUser(userDto);
        }).isInstanceOf(ValidationException.class)
                .hasMessage("Email value can not be empty.");

    }

    @Test
    @DisplayName("Test should pass find user by id")
    void shouldFindUserById() {

        User user = new User(1, "Mustafa Uğur", "KAÇAK", "ugurkacak@yandex.com", "letmein", "user");

        UserDto expectedUserDto = UserDto.builder()
                .id(1)
                .firstName("Mustafa Uğur")
                .lastName("Kaçak")
                .email("ugurkacak@yandex.com")
                .password("letmein")
                .role("user")
                .build();

        // Mocking
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.getUserById(1)).thenReturn(user);
        when(userConverter.userToUserDto(any(User.class))).thenReturn(expectedUserDto);

        UserDto actualUserDto = userService.getUser(1);

        assertThat(actualUserDto.getId()).isEqualTo(expectedUserDto.getId());
        assertThat(actualUserDto.getFirstName()).isEqualTo(expectedUserDto.getFirstName());
        assertThat(actualUserDto.getLastName()).isEqualTo(expectedUserDto.getLastName());
        assertThat(actualUserDto.getEmail()).isEqualTo(expectedUserDto.getEmail());
        assertThat(actualUserDto.getPassword()).isEqualTo(expectedUserDto.getPassword());
        assertThat(actualUserDto.getRole()).isEqualTo(expectedUserDto.getRole());
    }

    @Test
    @DisplayName("Test should pass when not register existing email")
    void shouldNotRegisterExistingEmail(){

        User dummyUser = new User();
        UserDto dummyUserDto = UserDto.builder()
                .id(1)
                .firstName("Mustafa Uğur")
                .lastName("Kaçak")
                .email("ugurkacak@yandex.com")
                .password("letmein")
                .role("user")
                .build();
        when(userRepository.getUserByEmail(any(String.class))).thenReturn(dummyUser);

        assertThatThrownBy(() -> {
            userService.register(dummyUserDto);
        }).isInstanceOf(BusinessException.class)
                .hasMessage("Email address is already in use");

    }

    @Test
    @DisplayName("Test should pass when register user")
    void shouldRegisterUser() throws ValidationException, BusinessException {
        User inputUser = new User(1, "Mustafa Uğur", "KAÇAK", "ugurkacak@yandex.com", "letmein", "user");

        UserDto dummyUserDto = UserDto.builder()
                .id(1)
                .firstName("Mustafa Uğur")
                .lastName("KAÇAK")
                .email("ugurkacak@yandex.com")
                .password("letmein")
                .role("user")
                .build();

        String sha256Pass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(inputUser.getPassword());
        inputUser.setPassword(sha256Pass);

        when(userRepository.getUserByEmail(dummyUserDto.getEmail()))
                .thenReturn(null);

        when(userConverter.userDtoToUser(any(UserDto.class))).thenReturn(inputUser);

        when(userRepository.save(any(User.class))).thenReturn(inputUser);

        when(userConverter.userToUserDto(any(User.class))).thenReturn(dummyUserDto);

        UserDto registeredUserDto = userService.register(dummyUserDto);

        assertThat(registeredUserDto.getId()).isEqualTo(inputUser.getId());
        assertThat(registeredUserDto.getFirstName()).isEqualTo(inputUser.getFirstName());
        assertThat(registeredUserDto.getLastName()).isEqualTo(inputUser.getLastName());
        assertThat(registeredUserDto.getEmail()).isEqualTo(inputUser.getEmail());
        assertThat(registeredUserDto.getPassword()).isEqualTo(inputUser.getPassword());
        assertThat(registeredUserDto.getRole()).isEqualTo(inputUser.getRole());
    }

}