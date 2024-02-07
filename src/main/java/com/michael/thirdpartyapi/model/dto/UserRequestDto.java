package com.michael.thirdpartyapi.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message= "email cannot be null")
    private String email;

    @NotBlank(message= "name cannot be null")
    private String name;

    @NotBlank(message=" address cannot be null")
    private  String address;

    @NotBlank(message=" enter a valid phone number")
    private String phoneNumber;


}
