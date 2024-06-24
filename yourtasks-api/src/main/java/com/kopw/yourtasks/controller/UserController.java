package com.kopw.yourtasks.controller;

import com.kopw.yourtasks.model.TaskDto;
import com.kopw.yourtasks.model.UserDto;
import com.kopw.yourtasks.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Apis")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<UserDto, Set<TaskDto>>> uploadFile(@RequestParam MultipartFile file) {
        return userService.importFile(file);
    }

}
