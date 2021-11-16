package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        //create new schedule object
        Schedule newSchedule = this.scheduleService.saveSchedule(this.convertScheduleDTOtoSchedule(scheduleDTO));

        scheduleDTO.setId(newSchedule.getId());

        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }

    private Schedule convertScheduleDTOtoSchedule(ScheduleDTO scheduleDTO){
        //create new blank schedule
        Schedule schedule = new Schedule();

        // fetch lists of employeeIds and petIds from scheduleDTO
        List<Long> employeeIds = scheduleDTO.getEmployeeIds();
        List<Long> petIds = scheduleDTO.getPetIds();

        //copy list of employeeIds and petIds from scheduleDTO to schedule
        BeanUtils.copyProperties(scheduleDTO, schedule);

        //initialize temporary employee and pet list objects
        List<Employee> employees = Lists.newArrayList();
        List<Pet> pets = Lists.newArrayList();

        //populate temporary employee and pet list objects from employeeId and petId
        employeeIds.forEach((employeeId) ->
                employees.add(employeeService.findEmployeeById(employeeId)));
        petIds.forEach((petId) ->
                pets.add(petService.findPet(petId)));

        //add list of employees and pets to schedule object
        schedule.setEmployees(employees);
        schedule.setPets(pets);

        return schedule;
    }

}
