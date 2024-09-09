package com.example.projectdemo2.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_user_profile")
public class UserProfile {

    @Id
    @Column(
            name = "id",
            nullable = false,
            unique = true

    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "account_id")
    Long accountId;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "province")
    String province;
    
}
