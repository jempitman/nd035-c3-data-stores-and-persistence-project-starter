package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;

import java.util.List;

/**
 * Interface to persist schedule entities
 */

public interface ScheduleRepository {

    Schedule saveSchedule (Schedule schedule);
    Schedule findScheduleById (Long id);
    void deleteSchedule(Schedule schedule);

    List<Schedule> getAllSchedules();

    List<Schedule> findScheduleByPet(Pet pet);

    List<Schedule> findScheduleByEmployee(Employee employee);


}
