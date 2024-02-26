package com.microservice.nttdata.dto;

import com.microservice.nttdata.validators.ConfigurableRegex;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SignUpUserDto {
    protected String name;
    @NotBlank(message = "El correo electrónico no puede estar en blanco")
    @Email(message = "Formato de correo electrónico inválido")
    protected String email;
    @NotBlank(message = "El Password no puede estar en blanco")
    @ConfigurableRegex
    protected String password;


    protected List<SignUpUserPhoneDto> phones;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<SignUpUserPhoneDto> getPhones() {
        return phones;
    }
    public void setPhones(List<SignUpUserPhoneDto> phones) {
        this.phones = phones;
    }
}
