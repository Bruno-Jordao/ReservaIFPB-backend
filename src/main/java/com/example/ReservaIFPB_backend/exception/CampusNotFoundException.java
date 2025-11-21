package com.example.ReservaIFPB_backend.exception;

public class CampusNotFoundException extends RuntimeException {

    public CampusNotFoundException(Long id) {
        super("Campus with id " + id + " not found");
    }
}
