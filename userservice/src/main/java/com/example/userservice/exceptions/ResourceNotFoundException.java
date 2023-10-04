package com.example.userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    
   public ResourceNotFoundException(){
    super("Resourse Not Found on server !!");
   }
   public ResourceNotFoundException(String message){
    super(message);
   }
}
