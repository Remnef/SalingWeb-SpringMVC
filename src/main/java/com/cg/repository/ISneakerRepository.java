package com.cg.repository;

import com.cg.model.Category;
import com.cg.model.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISneakerRepository extends JpaRepository<Sneaker, Long> {
    Optional<Sneaker> findTopByStatusIsTrueOrderByIdDesc();

    Iterable<Sneaker> findAllByStatusIsTrue();

    Iterable<Sneaker> findAllByOrderByIdDesc();

    Iterable<Sneaker> findAllByCategoryAndStatusIsTrue(Category category);

    Iterable<Sneaker> findByNameContaining(String keyword);

    Optional<Sneaker> findByName(String name);

    Iterable<Sneaker> findAllByStatusIsTrueOrderByIdDesc();


}
