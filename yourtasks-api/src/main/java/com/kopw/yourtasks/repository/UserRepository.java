package com.kopw.yourtasks.repository;

import com.kopw.yourtasks.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
