package com.example.ReservaIFPB_backend.exception;

public class BlockAlreadyExistsException extends RuntimeException {

    public BlockAlreadyExistsException(String message) {
        super(message);
    }
}
