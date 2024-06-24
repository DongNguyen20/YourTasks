package com.kopw.yourtasks.service;

import com.kopw.yourtasks.model.TaskDto;
import com.kopw.yourtasks.model.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
    List<UserDto> findAll();

    ResponseEntity<Map<UserDto, Set<TaskDto>>> importFile(MultipartFile file);
}
