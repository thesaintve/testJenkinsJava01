package com.microservice.nttdata.controller;

import com.microservice.nttdata.dto.SignUpUserDto;
import com.microservice.nttdata.dto.CreatedUserDto;
import com.microservice.nttdata.entity.User;
import com.microservice.nttdata.exceptios.CustomException;
import com.microservice.nttdata.service.JwtUtil;
import com.microservice.nttdata.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/user")
@Validated
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<CreatedUserDto> addUser(@Validated @RequestBody SignUpUserDto signUpUserDto, BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                User uCreated = userService.createUser(signUpUserDto);
                CreatedUserDto userResponse = new CreatedUserDto(uCreated.getId().toString(), uCreated.getName(), uCreated.getEmail(), uCreated.getActive(), uCreated.getCreated(), uCreated.getLastLogin(), uCreated.getModified(), jwtUtil.generateToken(uCreated.getEmail()));

                return ResponseEntity.ok(userResponse);
            }
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.joining(", ", "Fallo en validación [ ", " ] "));
            throw new CustomException(errorMessage, HttpStatus.BAD_REQUEST);
        }
        catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new CustomException("El correo ya registrado", HttpStatus.BAD_REQUEST);
        }
        catch (CustomException e) {
            throw e;
        }
        catch (Exception e) {
            throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
