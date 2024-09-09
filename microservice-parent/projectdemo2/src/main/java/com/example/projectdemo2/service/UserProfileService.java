package com.example.projectdemo2.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.example.projectdemo2.dto.UserProfileDTO;



public interface UserProfileService {

    UserProfileDTO save(UserProfileDTO dto, Long id);
    Page<UserProfileDTO> getPage(int pageSize, int pageIndex);
    List<UserProfileDTO> getAll ();
    UserProfileDTO destroy(Long id);

}
