package com.brand0nny.springboot.web.abarrotes_tepari.dto;

import java.util.Set;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    String username;
    String firstname;
    String lastname;
    int age;
    String email;
    Set<Role> role;

}
