package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        //create new schedule object
        Schedule newSchedule = this.scheduleService.saveSchedule(this.convertScheduleDTOtoSchedule(scheduleDTO));

        //assign newly created ID to scheduleDTO
        scheduleDTO.setId(newSchedule.getId());

        return scheduleDTO;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        //Fetch list of all schedules and convert to DTO
        List<Schedule> scheduleList = scheduleService.getAllSchedules();

        return scheduleList.stream()
                .map(ScheduleController::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        List<Schedule> petSchedule = scheduleService.getScheduleByPet(petId);

        return petSchedule.stream()
                .map(ScheduleController::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());

    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> employeeSchedule = scheduleService.getScheduleByEmployee(employeeId);

        return employeeSchedule.stream()
                .map(ScheduleController::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {

        List<Pet> customerPets = petService.getPetsByOwnerId(customerId);

        List<Schedule> customerSchedule = new ArrayList<>();

        customerPets.forEach((pet) ->
                customerSchedule.addAll(scheduleService.getScheduleByPet(pet.getId())));

        return customerSchedule.stream()
                .map(ScheduleController::convertScheduleToScheduleDTO)
                .collect(Collectors.toList());
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

    private static ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule){
        //create new blank scheduleDTO
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        //transfer schedule details to scheduleDTO objects
        BeanUtils.copyProperties(schedule, scheduleDTO);

        //initialize temporary lists for pet and employee IDs
        List<Long> employeeIds = new ArrayList<>();
        List<Long> petIds = new ArrayList<>();

        if (schedule.getEmployees() != null){
            schedule.getEmployees().forEach(employee -> {
                employeeIds.add(employee.getId());
            });

            scheduleDTO.setEmployeeIds(employeeIds);
        }

        if (schedule.getPets() != null){
            schedule.getPets().forEach(pet -> {
                petIds.add(pet.getId());
            });
            scheduleDTO.setPetIds(petIds);
        }

        return scheduleDTO;
    }

}
