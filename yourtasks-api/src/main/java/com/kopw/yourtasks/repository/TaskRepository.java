package com.kopw.yourtasks.repository;

import com.kopw.yourtasks.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
