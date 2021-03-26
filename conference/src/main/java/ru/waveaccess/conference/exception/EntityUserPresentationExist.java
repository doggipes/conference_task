package ru.waveaccess.conference.exception;

import javax.persistence.EntityExistsException;

public class EntityUserPresentationExist extends EntityExistsException {
    public EntityUserPresentationExist(String msg){
        super(msg);
    }
}
