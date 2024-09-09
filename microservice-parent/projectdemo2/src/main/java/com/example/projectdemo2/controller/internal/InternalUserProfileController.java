package com.example.projectdemo2.controller.internal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.projectdemo2.api.ApiResponseWrapper;
import com.example.projectdemo2.dto.UserProfileDTO;
import com.example.projectdemo2.service.UserProfileService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/internal/profile")
@AllArgsConstructor
@Slf4j
public class InternalUserProfileController {
    
    private final UserProfileService userProfileSvc;
    

    @RequestMapping(value = "/create",method=RequestMethod.POST)
    public ResponseEntity<ApiResponseWrapper<UserProfileDTO>> create(
            @RequestBody() @Valid UserProfileDTO dto,
            HttpServletRequest request) {

        log.info("Received request to save UserProfile with details: {}", dto);
        try {
            //create UserProfile
            UserProfileDTO createUserProfile = userProfileSvc.save(dto,null);
            ApiResponseWrapper<UserProfileDTO> result = new ApiResponseWrapper<>(
                HttpStatus.OK.value(),
                "success",
                createUserProfile);
            log.info("UserProfile saved successfully with details: {}", dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while saving UserProfile: {}", e.getMessage(), e);
            // response errror
            ApiResponseWrapper<UserProfileDTO> result = new ApiResponseWrapper<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
               "notfound", 
                null);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/save/{id}",method=RequestMethod.POST)
    public ResponseEntity<ApiResponseWrapper<UserProfileDTO>> saveById(
                @RequestBody() @Valid UserProfileDTO dto,
                @PathVariable Long id,
                HttpServletRequest request) {

        log.info("Received request to save UserProfile with details: {}", dto);
        try {
            //save UserProfile
            UserProfileDTO savedUserProfile = userProfileSvc.save(dto, id);

            if (savedUserProfile == null
                || id == null ) {
                ApiResponseWrapper<UserProfileDTO> result = new ApiResponseWrapper<>(
                    HttpStatus.NOT_FOUND.value(),
                    "UserProfile notfound",
                    null);
                log.info("UserProfile with ID {} not found:", id);
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }   
                ApiResponseWrapper<UserProfileDTO> result = new ApiResponseWrapper<>(
                    HttpStatus.OK.value(),
                    "success",
                    savedUserProfile);
                log.info("UserProfile saved successfully with details: {}", dto);
                return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while saving UserProfile: {}", e.getMessage(), e);
            // response errror
            ApiResponseWrapper<UserProfileDTO> result = new ApiResponseWrapper<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), 
               "notfound",  
                null);
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/destroy/{id}",method=RequestMethod.POST)
    public ResponseEntity<ApiResponseWrapper<UserProfileDTO>> destroy(
                @PathVariable Long id,
                HttpServletRequest request) {

        log.info("Received request to destroy UserProfile with id: {}", id);
        try {
            UserProfileDTO dto = userProfileSvc.destroy(id);
            if (dto == null) {
                ApiResponseWrapper<UserProfileDTO> result = new ApiResponseWrapper<>(
                    HttpStatus.NOT_FOUND.value(),
                    "UserProfile notfound",
                null);
                log.info("UserProfile with ID {} not found:", id);
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
            }   
                ApiResponseWrapper<UserProfileDTO> result = new ApiResponseWrapper<>(
                    HttpStatus.OK.value(),
                    "UserProfile destroy success",
                    dto);
                log.info("Destroy UserProfile with ID {}", id);
                return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "error";
            return new ResponseEntity<>(new ApiResponseWrapper<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
