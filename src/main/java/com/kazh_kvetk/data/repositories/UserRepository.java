package com.kazh_kvetk.data.repositories;

import com.kazh_kvetk.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends UserBaseRepository<User>{}