package com.teste.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.api.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    
}
