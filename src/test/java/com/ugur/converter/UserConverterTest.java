package com.ugur.converter;

import com.ugur.dto.UserDto;
import com.ugur.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserConverterTest {
    private UserConverter userConverter;

    @BeforeEach
    void setUp() {
        userConverter = new UserConverter();
    }

    @Test
    void userDtoToUser() {
        UserDto userDto = UserDto.builder()
                .id(1)
                .firstName("Uğur")
                .lastName("KAÇAK")
                .email("ugurkacak@yandex.com")
                .password("letmein")
                .role("user")
                .build();

        User user = userConverter.userDtoToUser(userDto);

        //assertThat(userDto.hashCode()).isEqualTo(user.hashCode());
        assertThat(userDto.getId()).isEqualTo(user.getId());
        assertThat(userDto.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userDto.getLastName()).isEqualTo(user.getLastName());
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDto.getPassword()).isEqualTo(user.getPassword());
        assertThat(userDto.getRole()).isEqualTo(user.getRole());
    }

    @Test
    void userToUserDto() {
        User user = new User(1, "Uğur", "KAÇAK", "ugurkacak@yandex.com", "letmein", "user");
        UserDto userDto = userConverter.userToUserDto(user);

        //assertThat(user.hashCode()).isEqualTo(userDto.hashCode());
        assertThat(user.getId()).isEqualTo(userDto.getId());
        assertThat(user.getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(user.getLastName()).isEqualTo(userDto.getLastName());
        assertThat(user.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(user.getPassword()).isEqualTo(userDto.getPassword());
        assertThat(user.getRole()).isEqualTo(userDto.getRole());
    }
}