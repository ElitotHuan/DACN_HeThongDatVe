package com.example.services;

import com.example.dto.ScheduleDTO;
import com.example.models.Schedule;
import com.example.repositories.BranchRepository;
import com.example.repositories.MovieRepository;
import com.example.repositories.RoomRepository;
import com.example.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public Boolean addSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        return setData(scheduleDTO, schedule);
    }

    public Boolean updateSchedule(int id, ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.getById(id);
        return setData(scheduleDTO, schedule);
    }

    public Boolean deleteSchedule(int id) {
        scheduleRepository.deleteById(id);
        return true;
    }

    private Boolean setData(ScheduleDTO scheduleDTO, Schedule schedule) {
        schedule.setPrice(scheduleDTO.getPrice());
        schedule.setStartDate(LocalDate.parse(scheduleDTO.getStartDate()));
        schedule.setStartTime(LocalTime.parse(scheduleDTO.getStartTime()));
        schedule.setMovie(movieRepository.getById(scheduleDTO.getMovieId()));
        schedule.setBranch(branchRepository.getById(scheduleDTO.getBranchId()));
        schedule.setRoom(roomRepository.searchByName(scheduleDTO.getRoomName(), scheduleDTO.getBranchId()));
        scheduleRepository.save(schedule);
        return true;
    }

}


