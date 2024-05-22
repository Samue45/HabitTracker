package com.HabitTracker.HabitTracker.Auth;

import com.HabitTracker.HabitTracker.User.Role;


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
    String firstName;
    String lastName;
    String password;
    Role role;

}
