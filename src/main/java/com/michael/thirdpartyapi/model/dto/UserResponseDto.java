package com.michael.thirdpartyapi.model.dto;

import java.util.Date;

public record UserResponseDto (
       Long id, String email, String name, String address, String phoneNumber, Date createdOn){
}
