package com.example.demo.service;

import com.example.demo.config.DTOMapper;
import com.example.demo.data.jpa.entity.UserEntity;
import com.example.demo.data.jpa.repository.UserRepository;
import com.example.demo.exception.PhoneNumberAlreadyTakenException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DTOMapper dtoMapper;
    private final CarService carService;

    @Transactional
    public Long create(UserCreateRequest userDTO){
        checkPhoneNumber(userDTO);
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userEntity);
        return userEntity.getId();
    }

    @Transactional(readOnly = true)
    public UserResponse getByPhoneNumber(String phoneNumber){
        UserEntity userEntity = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UserNotFoundException(phoneNumber));
        return dtoMapper.toDTO(userEntity);
    }

    @Transactional(readOnly = true)
    public UserResponse getByID(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return dtoMapper.toDTO(userEntity);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(dtoMapper::toDTO).collect(Collectors.toList());
    }

    private void checkPhoneNumber(BaseUserDTO userDTO) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
//        if (userRepository.exists(Example.of(userEntity))) {
//            throw new PhoneNumberAlreadyTakenException(userDTO.getPhoneNumber());
//        }
        if (userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())) {
            throw new PhoneNumberAlreadyTakenException(userDTO.getPhoneNumber());
        }
    }

    @Transactional(readOnly = true)
    public UserResponseWithCarList getWithCars(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        UserResponseWithCarList userResponseWithCarList = dtoMapper.toUserDTOWithCars(userEntity);
        List<BaseCarDTO> baseCarDTOList = carService.getCarsByUserId(id);
        userResponseWithCarList.setCarResponses(baseCarDTOList);
        return userResponseWithCarList;
    }

    public List<UserResponse> getUsersContainsString(String input) {
        List<UserEntity> userEntities = userRepository.findAllByNameLike("%" + input + "%");
        return userEntities.stream().map(dtoMapper::toDTO).collect(Collectors.toList());
    }
}