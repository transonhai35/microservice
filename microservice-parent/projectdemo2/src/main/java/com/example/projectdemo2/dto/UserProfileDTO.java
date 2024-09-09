package com.example.projectdemo2.dto;


import com.example.projectdemo2.entity.UserProfile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileDTO {
    
    Long id;
    Long accountId;
    String firstName;
    String lastName;
    String province;

    public UserProfileDTO(UserProfile userProfile) {
        if (userProfile != null) {
            this.id = userProfile.getId();
            this.firstName = userProfile.getFirstName();
            this.lastName = userProfile.getLastName();
            this.province = userProfile.getProvince();
            this.accountId = userProfile.getAccountId();
        }
    }
}
