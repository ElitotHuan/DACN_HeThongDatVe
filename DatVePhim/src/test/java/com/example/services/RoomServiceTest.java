package com.example.services;

import com.example.dto.RoomDTO;
import com.example.models.Branch;
import com.example.models.Room;
import com.example.repositories.BranchRepository;
import com.example.repositories.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void testGetAll() {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Room room = new Room();
            room.setId(i + 1);
            room.setName("Room" + (i + 1));
            room.setCapacity(10 * (i + 1));
            room.setTotalArea(100.0 * (i + 1));
            rooms.add(room);
        }

        when(roomRepository.findAll()).thenReturn(rooms);

        List<Room> result = roomService.getAll();
        assertEquals(rooms.size(), result.size());
    }

    @Test
    void testAddRoom() {
        Branch branch = new Branch();
        branch.setId(1);
        branch.setName("Branch1");
        branch.setDiaChi("Address1");
        branch.setPhoneNo("PhoneNo1");

        when(branchRepository.getById(1)).thenReturn(branch);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("New Room");
        roomDTO.setCapacity(10);
        roomDTO.setTotalArea(100.0);
        roomDTO.setBranchId(1);

        boolean result = roomService.addRoom(roomDTO);
        assertTrue(result);

        verify(roomRepository).save(any(Room.class));
    }

    @Test
    void testUpdateRoom() {
        Branch branch = new Branch();
        branch.setId(1);
        branch.setName("Branch1");
        branch.setDiaChi("Address1");
        branch.setPhoneNo("PhoneNo1");

        when(branchRepository.getById(1)).thenReturn(branch);

        Room room = new Room();
        room.setId(1);
        room.setName("Old Room");
        room.setCapacity(10);
        room.setTotalArea(100.0);
        room.setBranch(branch);

        when(roomRepository.getById(1)).thenReturn(room);

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("Updated Room");
        roomDTO.setCapacity(20);
        roomDTO.setTotalArea(200.0);
        roomDTO.setBranchId(1);

        boolean result = roomService.updateRoom(1, roomDTO);
        assertTrue(result);

        verify(roomRepository).save(any(Room.class));
    }

    @Test
    void testDeleteRoom() {
        boolean result = roomService.deleteRoom(1);
        assertTrue(result);

        verify(roomRepository).deleteById(1);
    }
}