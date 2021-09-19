package com.cg.repository;

import com.cg.model.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISneakerRepository extends JpaRepository<Sneaker, Long> {
    Optional<Sneaker> findTopByOrderByIdDesc();
}
