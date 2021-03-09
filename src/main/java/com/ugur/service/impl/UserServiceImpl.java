package com.ugur.service.impl;

import com.ugur.converter.UserConverter;
import com.ugur.dto.UserDto;
import com.ugur.entity.User;
import com.ugur.exception.BusinessException;
import com.ugur.exception.EntityNotFoundExceptionById;
import com.ugur.repository.UserRepository;
import com.ugur.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Override
    public UserDto register(UserDto userDto) throws ValidationException, BusinessException {

        validateUser(userDto);

        User user = userRepository.getUserByEmail(userDto.getEmail());

        if (user != null) {
            throw new BusinessException("Email address is already in use");
        }

        String sha256Pass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(userDto.getPassword());
        userDto.setPassword(sha256Pass);

        User registeredUser = userRepository.save(userConverter.userDtoToUser(userDto));

        log.info("User register operation successful" + registeredUser);
        return userConverter.userToUserDto(registeredUser);
    }

    @Override
    public UserDto login(final String email, final String password) throws BusinessException {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            throw new BusinessException("Email address or password incorrect.");
        }

        String sha256Pass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

        if (!user.getPassword().equalsIgnoreCase(sha256Pass)) {
            throw new BusinessException("Email address or password incorrect.");
        }

        log.info("User login operation successful" + user.getEmail());
        return userConverter.userToUserDto(user);
    }

    @Override
    public void delete(final Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundExceptionById("Invalid id for delete operation | id:" + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserByEmail(final String email) {
        return userConverter.userToUserDto(userRepository.getUserByEmail(email));
    }

    @Override
    public UserDto getUser(final Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundExceptionById("Specified user not found with id: " + id);
        }
        return userConverter.userToUserDto(userRepository.getUserById(id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void validateUser(UserDto userDto) throws ValidationException {
        if (isNull(userDto)) {
            throw new ValidationException("User object is null.");
        }
        if (isNull(userDto.getEmail()) || userDto.getEmail().isEmpty()) {
            throw new ValidationException("Email value can not be empty.");
        }
    }
}
