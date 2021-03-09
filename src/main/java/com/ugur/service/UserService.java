package com.ugur.service;

import com.ugur.dto.UserDto;
import com.ugur.exception.BusinessException;
import com.ugur.exception.EntityNotFoundExceptionById;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface UserService {

    UserDto register(UserDto userDto) throws ValidationException, BusinessException;

    UserDto login(String email, String password) throws BusinessException;

    void delete(Integer id) throws EntityNotFoundExceptionById;

    UserDto getUserByEmail(String email);

    UserDto getUser(Integer id);

    List<UserDto> getAllUsers();

    void validateUser(UserDto userDto) throws ValidationException;
}
