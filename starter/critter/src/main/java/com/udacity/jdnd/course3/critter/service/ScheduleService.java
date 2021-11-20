package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepositoryImpl scheduleRepository;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    //save new schedule with multiple employee and pet IDs
    public Schedule saveSchedule(Schedule schedule){
        return scheduleRepository.saveSchedule(schedule);
    }

    //fetch all schedules
    public List<Schedule> getAllSchedules(){
        return scheduleRepository.getAllSchedules();
    }

    //find all schedules according to petId
    public List<Schedule> getScheduleByPet(Long petId){

        return scheduleRepository.findScheduleByPet(petService.findPet(petId));

    }

    //find all schedules according to petId
    public List<Schedule> getScheduleByEmployee(Long employeeId){
        return scheduleRepository.findScheduleByEmployee(employeeId);

    }







}
