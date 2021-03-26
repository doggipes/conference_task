package ru.waveaccess.conference.exception;

import javax.persistence.EntityNotFoundException;

public class RoomEntityNotFound extends EntityNotFoundException {
    public RoomEntityNotFound(String msg){
        super(msg);
    }
}
