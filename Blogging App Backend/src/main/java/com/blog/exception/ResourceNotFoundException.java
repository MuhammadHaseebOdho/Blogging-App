package com.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    String message;
    Integer id;

    public ResourceNotFoundException(Integer id,String message){
        this.message=message;
        this.id=id;
    }


}
