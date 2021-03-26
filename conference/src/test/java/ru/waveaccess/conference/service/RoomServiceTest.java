package ru.waveaccess.conference.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import ru.waveaccess.conference.model.dto.RoomDto;
import ru.waveaccess.conference.model.entity.Room;
import ru.waveaccess.conference.repository.RoomRepository;
import ru.waveaccess.conference.service.impl.RoomServiceImpl;
import ru.waveaccess.conference.service.interfaces.RoomService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class RoomServiceTest {

    @Autowired
    private RoomRepository roomRepository;


    @Test
    public void saveRoomTest() {
        Room room = Room.builder()
                        .id(1L)
                        .name("1")
                        .build();
        RoomService roomService = new RoomServiceImpl(roomRepository);

        RoomDto roomDto = RoomDto.builder()
                                .id(room.getId())
                                .name(room.getName())
                                .build();

        Room roomSave = roomService.createRoom(roomDto);

        Assert.isTrue(roomSave.getName().equals("1"));
    }

    @Test
    public void getAllRoomsTest(){
        Room room1 = Room.builder()
                        .name("1")
                        .build();
        Room room2 = Room.builder()
                        .name("2")
                        .build();
        Room room3 = Room.builder()
                        .name("3")
                        .build();
        Room room4 = Room.builder()
                        .name("4")
                        .build();

        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);

        RoomDto roomDto1 = RoomDto.builder()
                .id(room1.getId())
                .name(room1.getName())
                .build();
        RoomDto roomDto2 = RoomDto.builder()
                .id(room2.getId())
                .name(room2.getName())
                .build();
        RoomDto roomDto3 = RoomDto.builder()
                .id(room3.getId())
                .name(room3.getName())
                .build();
        RoomDto roomDto4 = RoomDto.builder()
                .id(room3.getId())
                .name(room3.getName())
                .build();

        RoomService roomService = new RoomServiceImpl(roomRepository);
        roomService.createRoom(roomDto1);
        roomService.createRoom(roomDto2);
        roomService.createRoom(roomDto3);
        roomService.createRoom(roomDto4);

        List<Room> checkedRoom = roomService.getAllRooms();

        Assert.isTrue(checkedRoom.size() == rooms.size());
    }
}
