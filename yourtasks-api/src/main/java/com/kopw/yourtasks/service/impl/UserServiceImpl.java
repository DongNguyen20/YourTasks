package com.kopw.yourtasks.service.impl;

import com.kopw.yourtasks.model.TaskDto;
import com.kopw.yourtasks.model.UserDto;
import com.kopw.yourtasks.repository.UserRepository;
import com.kopw.yourtasks.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(u -> UserDto.builder().id(u.getId())
                .name(u.getName())
                .build()).toList();
    }

    @Override
    public ResponseEntity<Map<UserDto, Set<TaskDto>>> importFile(MultipartFile file) {
        Map<UserDto, Set<TaskDto>> resultMap = new HashMap<>();
        Map<String, Integer> taskIndexes = new HashMap<>();

        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cellA = row.getCell(0);
                Cell cellB = row.getCell(1);
                if (cellA != null) {
                    String userName = cellA.getStringCellValue();
                    UserDto user = UserDto.builder().name(userName).build();
                    if (!resultMap.containsKey(user)) {
                        resultMap.put(user, new LinkedHashSet<>());
                    }
                    if (cellB != null) {
                        String taskTitle = cellB.getStringCellValue();
                        taskIndexes.putIfAbsent(userName, 1);
                        int taskIndex = taskIndexes.get(userName);
                        TaskDto task = TaskDto.builder().name(taskTitle).build();
                        if (!resultMap.get(user).contains(task)) {
                            resultMap.get(user).add(task);
                            taskIndexes.put(userName, taskIndex + 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
