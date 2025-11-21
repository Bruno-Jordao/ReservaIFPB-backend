package com.example.ReservaIFPB_backend.exception;

public class CampusAlreadyExistsException extends RuntimeException {

    public CampusAlreadyExistsException(String name) {
        super("Campus with name '" + name + "' already exists");
    }
}
