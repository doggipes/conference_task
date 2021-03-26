package ru.waveaccess.conference.service.interfaces;

import ru.waveaccess.conference.model.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    void createRoom();

    Room findByNumber(String number);

    List<Room>  getAllRooms();

    List<String> getAllRoomNames();
}
