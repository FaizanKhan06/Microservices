package com.demo.authentication_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.authentication_service.entity.UserCredentialsEntity;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentialsEntity, Integer>{
    public Optional<UserCredentialsEntity> findByName(String name);

    
}
