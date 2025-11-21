package com.example.ReservaIFPB_backend.exception;

public class RoomAlreadyExistsException extends RuntimeException {

    public RoomAlreadyExistsException(String message) {
        super(message);
    }
}
