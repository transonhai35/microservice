package com.example.projectdemo2.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectdemo2.api.ApiResponseWrapper;
import com.example.projectdemo2.dto.UserProfileDTO;
import com.example.projectdemo2.service.UserProfileService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
@Slf4j
public class UserProfileController {
    
    private final UserProfileService userProfileSvc;
    
    @RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<?> getPage(
        @PathVariable int pageIndex, 
        @PathVariable int pageSize) {

        try {
            //get user profile
            Page<UserProfileDTO> getUserProfiles = userProfileSvc.getPage(pageSize,pageIndex);
            ApiResponseWrapper<Page<UserProfileDTO>> result = new ApiResponseWrapper<>(
                HttpStatus.OK.value(), 
               "success",
                getUserProfiles);
            log.info("Successfully retrieved UserProfile list with details: {}", result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while retrieved UserProfile: {}", e.getMessage(), e);
            // response errror
            ApiResponseWrapper<UserProfileDTO> result = new ApiResponseWrapper<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "resource not found",
                null);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

    @RequestMapping(value = "/getAll", method=RequestMethod.GET)
    public ResponseEntity<?> getAll(HttpServletRequest request) {

        try {
            //get user profile
            List<UserProfileDTO> getUserProfiles = userProfileSvc.getAll();
            ApiResponseWrapper<List<UserProfileDTO>> result = new ApiResponseWrapper<>(
                HttpStatus.OK.value(), 
                "success",
                getUserProfiles);
            log.info("Successfully retrieved UserProfile list with details: {}", result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while retrieved UserProfile: {}", e.getMessage(), e);
            // response errror
            ApiResponseWrapper<UserProfileDTO> result = new ApiResponseWrapper<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "have error",
                null);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
