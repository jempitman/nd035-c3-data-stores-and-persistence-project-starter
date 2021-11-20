package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

    @Autowired
    EntityManager entityManager;

    private static final String FIND_ALL_SCHEDULES = "select s from Schedule s";
    private static final String FIND_SCHEDULE_BY_PET = "select s from Schedule s where :pet member of s.pets";;
    private static final String FIND_SCHEDULE_BY_EMPLOYEE = "select s from Schedule s where :employee member of s.employees";


    @Override
    public Schedule saveSchedule(Schedule s) {

        if(s.getId() == null || s.getId() <= 0){
            entityManager.persist(s);
        } else{
            s = entityManager.merge(s);
        }

        return s;
    }

    @Override
    public Schedule findScheduleById(Long id) {
        return entityManager.find(Schedule.class, id);
    }

    @Override
    public void deleteSchedule(Schedule s) {
        if (entityManager.contains(s)){
            entityManager.remove(s);
        }else{
            entityManager.remove(entityManager.merge(s));
        }

    }

    @Override
    public List<Schedule> getAllSchedules() {
        TypedQuery<Schedule> query = entityManager.createQuery(FIND_ALL_SCHEDULES, Schedule.class);
        return query.getResultList();
    }

    @Override
    public List<Schedule> findScheduleByPet(Pet pet) {
        return entityManager
                .createQuery(FIND_SCHEDULE_BY_PET, Schedule.class)
                .setParameter("pet", pet)
                .getResultList();
    }

    @Override
    public List<Schedule> findScheduleByEmployee(Employee employee) {
        return entityManager
                .createQuery(FIND_SCHEDULE_BY_EMPLOYEE, Schedule.class)
                .setParameter("employee", employee)
                .getResultList();
    }
}
