package com.ummugul.libraryapi.controller;

import com.ummugul.libraryapi.dto.UserDto;
import com.ummugul.libraryapi.dto.UserRequest;
import com.ummugul.libraryapi.dto.UserResponse;
import com.ummugul.libraryapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> saveuser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok((UserResponse) authenticationService.save(userDto));
    }


    @PostMapping("/auth")
    public ResponseEntity<UserResponse> authuser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }
}