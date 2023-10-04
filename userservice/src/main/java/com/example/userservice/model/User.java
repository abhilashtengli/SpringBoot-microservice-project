package com.example.userservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "User")
public class User {

    @Id
    private String UserId;
    private String UserName;
    private String UserEmail;
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
