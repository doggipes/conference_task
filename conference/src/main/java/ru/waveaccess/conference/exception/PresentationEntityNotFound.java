package ru.waveaccess.conference.exception;

import javax.persistence.EntityNotFoundException;

public class PresentationEntityNotFound extends EntityNotFoundException {
    public PresentationEntityNotFound(String msg){
        super(msg);
    }
}
