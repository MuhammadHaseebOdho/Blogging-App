package com.blog.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;
    @NotEmpty(message = "Username can not be blank")
    @Size(min = 6,max = 20,message = "Username must be within 6 to 20 characters")
    private String name;
    @Email(message = "Please enter a valid email")
    @NotEmpty(message = "Email can not be blank")
    private String email;
    @NotEmpty(message = "Password can not be blank")
    @Size(min = 6,max = 20,message = "Password must be within 6 to 20 characters")
    private String password;
    @NotEmpty
    private String about;
}
