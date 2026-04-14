package com.example.hospitalManagement.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String name,Long id){
        super(name+" not found with id: " +id);
    }
}
