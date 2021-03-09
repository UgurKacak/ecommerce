package com.ugur.controller;

import com.ugur.dto.UserDto;
import com.ugur.exception.BusinessException;
import com.ugur.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Api(value = "User Api documentation")
public class UserController {

    @Value("${api.secret.key}")
    @Setter
    private String secretKey;

    @Value("${api.token.expiration}")
    @Setter
    private String tokenExpiration;

    private final UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "User login method")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDto userDto) throws BusinessException {
        log.info("User login operation invoked for: " + userDto.getEmail());
        UserDto loggedUserDto = userService.login(userDto.getEmail(), userDto.getPassword());
        return new ResponseEntity<>(generateJwtToken(loggedUserDto), HttpStatus.OK);
    }

    @PostMapping("/register")
    @ApiOperation(value = "User register method")
    public UserDto register(@RequestBody UserDto userDto) throws ValidationException, BusinessException {
        log.info("User register operation invoked for: " + userDto);
        return userService.register(userDto);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "User delete method")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        log.info("Delete user operation invoked for: " + id);
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by id method")
    public UserDto getUser(@PathVariable Integer id) {
        log.info("Get user operation invoked for: " + id);
        return userService.getUser(id);
    }

    @GetMapping("/getAllUsers")
    @ApiOperation(value = "Get all users method")
    public List<UserDto> getAllUsers() {
        log.info("Get all users request invoked.");
        return userService.getAllUsers();
    }

    private Map<String, String> generateJwtToken(UserDto userDto) {
        Map<String, String> map = new HashMap<>();
        map.put("token", getToken(userDto));
        return map;
    }

    private String getToken(UserDto userDto) {
        long timeStamp = System.currentTimeMillis();
        return Jwts.builder().signWith(SignatureAlgorithm.HS256, secretKey)
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + Long.parseLong(tokenExpiration)))
                .claim("id", userDto.getId())
                .claim("firstName", userDto.getFirstName())
                .claim("lastName", userDto.getLastName())
                .claim("email", userDto.getEmail())
                .compact();
    }

}
