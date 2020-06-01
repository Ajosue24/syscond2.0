package com.sycond.authentication.response;

import com.sycond.authentication.entity.Company;
import com.sycond.authentication.entity.security.Roles;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class UserResponse {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private Company company;

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String documentNumber;

    @Getter
    @Setter
    private Set<Roles> rolesSet = new HashSet<>();

    @Getter
    @Setter
    private Boolean isEnabled;

}
