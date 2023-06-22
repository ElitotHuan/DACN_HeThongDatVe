package com.example.services;

import com.example.dto.ScheduleDTO;
import com.example.models.Branch;
import com.example.models.Movie;
import com.example.models.Room;
import com.example.models.Schedule;
import com.example.repositories.BranchRepository;
import com.example.repositories.MovieRepository;
import com.example.repositories.RoomRepository;
import com.example.repositories.ScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    void testGetAll() {
        List<Schedule> schedules = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Schedule schedule = new Schedule();
            schedule.setId(i + 1);
            schedule.setPrice(10.0 * (i + 1));
            schedule.setStartDate(LocalDate.now());
            schedule.setStartTime(LocalTime.now());
            schedules.add(schedule);
        }

        when(scheduleRepository.findAll()).thenReturn(schedules);

        List<Schedule> result = scheduleService.getAll();
        assertEquals(schedules.size(), result.size());
    }

    @Test
    void testAddSchedule() {
        Branch branch = new Branch();
        branch.setId(1);
        branch.setName("Branch1");
        branch.setDiaChi("Address1");
        branch.setPhoneNo("PhoneNo1");

        when(branchRepository.getById(1)).thenReturn(branch);

        Movie movie = new Movie();
        movie.setId(1);
        movie.setName("Movie1");
        movie.setReleaseDate(LocalDate.now());
        movie.setLargeImageURL("LargeImageURL1");
        movie.setSmallImageURl("SmallImageURL1");
        movie.setCategories("Categories1");
        movie.setDescription("Description1");
        movie.setDuration(120);
        movie.setActors("Actors1");
        movie.setDirector("Director1");
        movie.setLanguage("Language1");

        when(movieRepository.getById(1)).thenReturn(movie);

        Room room = new Room();
        room.setId(1);
        room.setName("Room1");
        room.setCapacity(10);
        room.setTotalArea(100.0);
        room.setBranch(branch);

        when(roomRepository.searchByName("Room1", 1)).thenReturn(room);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setPrice(10.0);
        scheduleDTO.setStartDate(LocalDate.now().toString());
        scheduleDTO.setStartTime(LocalTime.now().toString());
        scheduleDTO.setMovieId(1);
        scheduleDTO.setBranchId(1);
        scheduleDTO.setRoomName("Room1");

        boolean result = scheduleService.addSchedule(scheduleDTO);
        assertTrue(result);

        verify(scheduleRepository).save(any(Schedule.class));
    }

    @Test
    void testUpdateSchedule() {
        Branch branch = new Branch();
        branch.setId(1);
        branch.setName("Branch1");
        branch.setDiaChi("Address1");
        branch.setPhoneNo("PhoneNo1");

        when(branchRepository.getById(2)).thenReturn(branch);

        Movie movie = new Movie();
        movie.setId(2);
        movie.setName("Movie2");
        movie.setReleaseDate(LocalDate.now());
        movie.setLargeImageURL("LargeImageURL2");
        movie.setSmallImageURl("SmallImageURL2");
        movie.setCategories("Categories2");
        movie.setDescription("Description2");
        movie.setDuration(120);
        movie.setActors("Actors2");
        movie.setDirector("Director2");
        movie.setLanguage("Language2");

        when(movieRepository.getById(2)).thenReturn(movie);

        Room room = new Room();
        room.setId(2);
        room.setName("Room2");
        room.setCapacity(10);
        room.setTotalArea(100.0);
        room.setBranch(branch);

        when(roomRepository.searchByName("Room2", 2)).thenReturn(room);

        Schedule schedule = new Schedule();
        schedule.setId(2);
        schedule.setPrice(10.0);
        schedule.setStartDate(LocalDate.now());
        schedule.setStartTime(LocalTime.now());
        schedule.setMovie(movie);
        schedule.setBranch(branch);
        schedule.setRoom(room);

        when(scheduleRepository.getById(2)).thenReturn(schedule);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setPrice(20.0);
        scheduleDTO.setStartDate(LocalDate.now().toString());
        scheduleDTO.setStartTime(LocalTime.now().toString());
        scheduleDTO.setMovieId(2);
        scheduleDTO.setBranchId(2);
        scheduleDTO.setRoomName("Room2");

        boolean result = scheduleService.updateSchedule(2, scheduleDTO);
        assertTrue(result);

        verify(scheduleRepository).save(any(Schedule.class));
    }
}