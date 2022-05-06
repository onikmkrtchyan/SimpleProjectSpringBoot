package com.example.demo.service;

import com.example.demo.config.DTOMapper;
import com.example.demo.data.jpa.entity.CarEntity;
import com.example.demo.data.jpa.repository.CarRepository;
import com.example.demo.data.jpa.repository.UserRepository;
import com.example.demo.exception.CarNumberAlreadyTakenException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.dto.BaseCarDTO;
import com.example.demo.service.dto.CarCreateRequest;
import com.example.demo.service.dto.CarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final DTOMapper dtoMapper;
    private final UserRepository userRepository;

    @Transactional
    public Long create(CarCreateRequest carCreateRequest){
        checkCarNumber(carCreateRequest);
        checkID(carCreateRequest);
        CarEntity carEntity = new CarEntity();
        carEntity.setModel(carCreateRequest.getModel());
        carEntity.setColor(carCreateRequest.getColor());
        carEntity.setCarNumber(carCreateRequest.getCarNumber());
        carEntity.setUser(userRepository.getById(carCreateRequest.getUserId()));
        carRepository.save(carEntity);
        return carEntity.getId();
    }

    @Transactional(readOnly = true)
    public Page<CarResponse> getAll(PageRequest pageRequest) {
        Page<CarEntity> carEntities = carRepository.findAllBy(pageRequest);
        return carEntities.map(dtoMapper::toResponse);
    }

    private void checkID(CarCreateRequest carCreateRequest) {
        if(!userRepository.existsById(carCreateRequest.getUserId())){
            throw new UserNotFoundException(carCreateRequest.getUserId());
        }
    }

    private void checkCarNumber(CarCreateRequest carCreateRequest) {
        if(carRepository.existsByCarNumber(carCreateRequest.getCarNumber())){
            throw new CarNumberAlreadyTakenException(carCreateRequest.getCarNumber());
        }
    }

    public List<BaseCarDTO> getCarsByUserId(Long id) {
        List<CarEntity> carEntities = carRepository.findByUserId(id);
        return carEntities.stream().map(dtoMapper::toBaseCarDTO).collect(Collectors.toList());
    }
}
