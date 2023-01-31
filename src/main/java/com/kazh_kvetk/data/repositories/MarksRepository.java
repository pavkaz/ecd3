package com.kazh_kvetk.data.repositories;

import com.kazh_kvetk.data.entities.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks, Integer> {}
