package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;

import java.util.List;


public interface ScheduleRepository {

    Schedule saveSchedule (Schedule schedule);
    Schedule findScheduleById (Long id);
    void deleteSchedule(Schedule schedule);

    List<Schedule> getAllSchedules();

    List<Schedule> findScheduleByPet(Pet pet);

    List<Schedule> findScheduleByEmployee(Employee employee);


//    private static final String FIND_ALL_SCHEDULES = "select s from Schedule s";
//    private static final String FIND_SCHEDULE_BY_PET = "select s from Schedule s where :pet member of s.pets";
//    private static final String FIND_SCHEDULE_BY_EMPLOYEE = "select s from Pet p where :customer member of p.customers";
//
//
//
//    public Schedule findSchedule(Long id){
//        return entityManager.find(Schedule.class, id);
//    }
//
//    public Schedule mergeSchedule(Schedule schedule){
//        return entityManager.merge(schedule);
//    }
//
//    public void deleteSchedule(Long id){
//        Schedule schedule = entityManager.find(Schedule.class, id);
//        entityManager.remove(schedule);
//    }
//
//    public List<Schedule> getAllSchedules(){
//        TypedQuery<Schedule> query = entityManager.createQuery(FIND_ALL_SCHEDULES, Schedule.class);
//        return query.getResultList();
//    }
//
//    public List<Schedule> findScheduleByPet(Pet pet){
//        return entityManager
//                .createQuery(FIND_SCHEDULE_BY_PET, Schedule.class)
//                .setParameter("pet", pet)
//                .getResultList();
//    }
//
//    public List<Schedule> findScheduleByEmployee(Employee employee){
//        return entityManager
//                .createQuery(FIND_SCHEDULE_BY_EMPLOYEE, Schedule.class)
//                .setParameter("employee", employee)
//                .getResultList();
//    }



}
