package com.example.projectdemo2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.projectdemo2.dto.UserProfileDTO;
import com.example.projectdemo2.entity.UserProfile;
import java.util.*;

@Repository
public interface UserProfileReponsitory extends JpaRepository<UserProfile, Long>{

    @Query("select new com.example.projectdemo2.dto.UserProfileDTO(ed) from UserProfile ed")
	Page<UserProfileDTO> getListPage( Pageable pageable);

    @Query("select new com.example.projectdemo2.dto.UserProfileDTO(ed) from UserProfile ed")
    List<UserProfileDTO> getAllUserProfile();
}