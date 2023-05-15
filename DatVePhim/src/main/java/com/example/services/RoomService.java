package com.example.services;

import com.example.dto.RoomDTO;
import com.example.models.Room;
import com.example.repositories.BranchRepository;
import com.example.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BranchRepository branchRepository;

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Boolean addRoom(RoomDTO roomDTO) {
        Room newRoom = new Room();
        newRoom.setName(roomDTO.getName());
        newRoom.setCapacity(roomDTO.getCapacity());
        newRoom.setTotalArea(roomDTO.getTotalArea());
        newRoom.setBranch(branchRepository.getById(roomDTO.getBranchId()));
        roomRepository.save(newRoom);
        return true;
    }

    public Boolean updateRoom(int id, RoomDTO roomDTO) {
        Room newRoom = roomRepository.getById(id);
        newRoom.setName(roomDTO.getName());
        newRoom.setCapacity(roomDTO.getCapacity());
        newRoom.setTotalArea(roomDTO.getTotalArea());
        newRoom.setBranch(branchRepository.getById(roomDTO.getBranchId()));
        roomRepository.save(newRoom);
        return true;
    }

    public Boolean deleteRoom(int id) {
        roomRepository.deleteById(id);
        return true;
    }
}
