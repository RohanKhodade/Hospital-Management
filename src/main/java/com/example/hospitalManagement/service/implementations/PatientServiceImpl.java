package com.example.hospitalManagement.service.implementations;

import com.example.hospitalManagement.dto.InsuranceDto;
import com.example.hospitalManagement.dto.PatientDto;
import com.example.hospitalManagement.entity.Insurance;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.entity.Role;
import com.example.hospitalManagement.entity.User;
import com.example.hospitalManagement.exceptions.EntityNotFoundException;
import com.example.hospitalManagement.mapper.InsuranceMapper;
import com.example.hospitalManagement.mapper.PatientMapper;
import com.example.hospitalManagement.repository.InsuranceRepository;
import com.example.hospitalManagement.repository.PatientRepository;
import com.example.hospitalManagement.repository.UserRepository;
import com.example.hospitalManagement.service.services.InsuranceService;
import com.example.hospitalManagement.service.services.PatientService;
import com.example.hospitalManagement.util.AuthUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthUtil authUtil;
    public PatientServiceImpl(PatientRepository patientRepository,
                              PasswordEncoder passwordEncoder,
                              UserRepository userRepository,
                              AuthUtil authUtil){
        this.patientRepository=patientRepository;
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
        this.authUtil=authUtil;
    }

    @Override
    public PatientDto getPatient(Long id){
        Patient patient=patientRepository.findById(id).orElseThrow(
                ()->new EntityNotFoundException("patient",id)
        );
        authUtil.checkAccess(patient.getUser().getUsername());
        return PatientMapper.toDto(patient);
    }

    @Cacheable("allPatients")
    @Override
    public List<PatientDto> getAllPatients(int pageNumber, int pageSize){

        Pageable p=PageRequest.of(pageNumber,pageSize);
        Page<Patient> patientPage=patientRepository.findAll(p);
        List<Patient> patients=patientPage.getContent();
        List<PatientDto> dtoList=new ArrayList<>();
        for (Patient patient :patients){
            dtoList.add(PatientMapper.toDto(patient));
        }
        return dtoList;
    }

    @Override
    @CacheEvict(value="allPatients",allEntries=true)
    public String createPatient(PatientDto patientDto){
        User user=new User();
        user.setUsername(patientDto.getUsername());
        user.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        user.setRole(Role.ROLE_PATIENT);
        userRepository.save(user);
        Patient patient=PatientMapper.toEntity(patientDto);
        patient.setUser(user);
        patientRepository.save(patient);
        return "patient created";
    }

    @Override
    public InsuranceDto getInsurance(Long patientId){
        Patient patient=patientRepository.findById(patientId).orElseThrow(
                ()->new EntityNotFoundException("patient",patientId)
        );
        Insurance insurance=patient.getInsurance();
        if (insurance==null){
            throw new RuntimeException("Insurance not assigned to patient");
        }
        return InsuranceMapper.toDto(insurance);
    }
}