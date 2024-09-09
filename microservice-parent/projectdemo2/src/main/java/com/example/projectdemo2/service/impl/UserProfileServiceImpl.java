package com.example.projectdemo2.service.impl;

import com.example.projectdemo2.dto.UserProfileDTO;
import com.example.projectdemo2.entity.UserProfile;
import com.example.projectdemo2.repository.UserProfileReponsitory;
import com.example.projectdemo2.service.UserProfileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService{
    
    private final UserProfileReponsitory userProfileRepo;

    @Override
    public Page<UserProfileDTO> getPage(int pageSize, int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex-1, pageSize);
        return userProfileRepo.getListPage(pageable);
    }

    @Override
    public List<UserProfileDTO> getAll() {
        
        List<UserProfileDTO> userProfiles = userProfileRepo.getAllUserProfile();
            
        return userProfiles;
    }   

        @Override
        public UserProfileDTO save(UserProfileDTO dto, Long id){
            if(dto == null) {
                return null;
            }
    
            UserProfile entity = null;
    
            if (dto.getId() != null || id != null) {
                if (dto.getId() != null && !dto.getId().equals(id)) {
                    return null;
                }
                entity = userProfileRepo.findById(id).orElse(new UserProfile());
            
            }
    
            if (entity == null) {
                entity = new UserProfile();
            }
    
            
            // Save or update UserProfile
            entity.setFirstName(dto.getFirstName());;
            entity.setLastName(dto.getLastName());
            entity.setProvince(dto.getProvince());
            entity.setAccountId(dto.getAccountId());

            entity = userProfileRepo.save(entity);
    
    
            return new UserProfileDTO(entity);
        }

        @Override
    public UserProfileDTO destroy(Long id) {

        if(id != null){
            if (userProfileRepo.existsById(id)) {
                // Retrieve userProfile before deleting
                UserProfile userProfile = userProfileRepo.findById(id).orElseThrow(); 
                userProfileRepo.deleteById(id);

                // Convert entity to DTO before returning
                UserProfileDTO dto = new UserProfileDTO();
                dto.setId(userProfile.getId());
                dto.setFirstName(userProfile.getFirstName());
                dto.setLastName(userProfile.getLastName());
                dto.setProvince(userProfile.getProvince());

                return dto; 
            }
            return null;
        }
        return null;
    }


}
