package com.michael.thirdpartyapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.michael.thirdpartyapi.model.User;
import com.michael.thirdpartyapi.model.dto.UserRequestDto;
import com.michael.thirdpartyapi.model.dto.UserResponseDto;
import com.michael.thirdpartyapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    ObjectMapper mapper = new ObjectMapper();
    public List<UserResponseDto> getUsers (){
        List<User> users = userRepository.findAll();

        return  users.stream()
                .map(user-> mapper.convertValue(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserResponseDto findById ( Long id){
        User user = userRepository.findById(id).orElseThrow();
       return mapper.convertValue(user, UserResponseDto.class);
    }
    public void createUser(UserRequestDto userRequestDto) {
        try {
            // Build a User object from the UserRequestDto
            User newUser = User.builder()
                    .name(userRequestDto.getName())
                    .email(userRequestDto.getEmail())
                    .phoneNumber(userRequestDto.getPhoneNumber())
                    .address(userRequestDto.getAddress())
                    .build();

            // Save the User object to the repository
            userRepository.save(newUser);

        }catch (Exception ex){
            System.out.println("An error occurred while registering ");
        }
    }
}
