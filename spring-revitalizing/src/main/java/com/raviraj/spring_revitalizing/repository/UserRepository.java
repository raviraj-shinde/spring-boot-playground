package com.raviraj.spring_revitalizing.repository;

import com.raviraj.spring_revitalizing.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    //findByUsername
    Optional<UserEntity> findByUsername(String username);

}
