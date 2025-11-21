package com.example.ReservaIFPB_backend.exception;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String name) {
        super("Role not found with name: " + name);
    }
}