package com.brand0nny.springboot.web.abarrotes_tepari.payload.request;

import java.util.HashSet;
import java.util.Set;

import com.brand0nny.springboot.web.abarrotes_tepari.entities.user.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
String username;
String password;
String firstname;
String lastname;
int age;
String email;
Set<Role> roles = new HashSet<>();

}
