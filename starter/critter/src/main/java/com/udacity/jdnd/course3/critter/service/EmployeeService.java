package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO saveEmployee(Employee employee){
        employeeRepository.save(employee);
        return createEmployeeDTO(employee);
    }

    public EmployeeDTO findEmployeeById(Long employeeId){
        return createEmployeeDTO(employeeRepository.getOne(employeeId));
    }

    private EmployeeDTO createEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);

        return employeeDTO;
    }




}
