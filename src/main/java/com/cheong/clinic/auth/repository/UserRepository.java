package com.cheong.clinic.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cheong.clinic.auth.entity.User;


public interface UserRepository extends JpaRepository<User, String> {

}
