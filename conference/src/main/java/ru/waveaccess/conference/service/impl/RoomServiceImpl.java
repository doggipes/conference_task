package ru.waveaccess.conference.service.impl;

import org.springframework.stereotype.Service;
import ru.waveaccess.conference.exception.RoomEntityNotFound;
import ru.waveaccess.conference.model.entity.Room;
import ru.waveaccess.conference.repository.RoomRepository;
import ru.waveaccess.conference.service.interfaces.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public void createRoom() {

    }

    @Override
    public Room findByNumber(String number) {
        if(!roomRepository.findByName(number).isPresent())
            throw new RoomEntityNotFound("Room doesn't exist");

        return roomRepository.findByName(number).get();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<String> getAllRoomNames() {
        return getAllRooms().stream().map(Room::getName).collect(Collectors.toList());
    }


}
