package com.example.demo.service;

import com.example.demo.config.DTOMapper;
import com.example.demo.data.jpa.entity.PassportEntity;
import com.example.demo.data.jpa.repository.PassportRepository;
import com.example.demo.data.jpa.repository.UserRepository;
import com.example.demo.exception.IDAlreadyTakenException;
import com.example.demo.exception.UIDAlreadyTakenException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.dto.PassportCreateRequest;
import com.example.demo.service.dto.PassportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PassportService {
    private final PassportRepository passportRepository;
    private final UserRepository userRepository;
    private final DTOMapper dtoMapper;


    @Transactional
    public Long create(PassportCreateRequest passportCreateRequest){
            checkUID(passportCreateRequest);
            checkID(passportCreateRequest);
    PassportEntity passportEntity = new PassportEntity();
    passportEntity.setUID(passportCreateRequest.getUID());
    passportEntity.setUser(userRepository.getById(passportCreateRequest.getUserId()));
    passportRepository.save(passportEntity);
    return passportEntity.getId();
    }

    private void checkUID(PassportCreateRequest passportCreateRequest) {
        if(passportRepository.existsByUID(passportCreateRequest.getUID())){
            throw new UIDAlreadyTakenException(passportCreateRequest.getUID());
        }
    }

    private void checkID(PassportCreateRequest passportCreateRequest) {
        if(passportRepository.existsByUserId(passportCreateRequest.getUserId())){
            throw new IDAlreadyTakenException(passportCreateRequest.getUserId());
        }
        if(!userRepository.existsById(passportCreateRequest.getUserId())){
            throw new UserNotFoundException(passportCreateRequest.getUserId());
        }
    }

    @Transactional(readOnly = true)
    public Page<PassportResponse> getAll(PageRequest pageRequest) {
        Page<PassportEntity> passportEntities = passportRepository.findAllBy(pageRequest);
        return passportEntities.map(dtoMapper::toResponse);
    }

}
