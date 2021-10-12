package com.cg.service.Sneaker;

import com.cg.model.Category;
import com.cg.model.Sneaker;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ISneakerService extends IGeneralService<Sneaker> {
    Optional<Sneaker> findTopByOrderByIdDesc();

    Iterable<Sneaker> findAllByStatusIsTrue();

    Iterable<Sneaker> findAllByOrderByIdDesc();

    Iterable<Sneaker> findAllByCategoryAndStatusIsTrue(Long id);

    Iterable<Sneaker> findByKeyword(String keyword);

    Optional<Sneaker> findByName(String name);

    Sneaker setDelete(Optional<Sneaker> sneakerOptional);

    Iterable<Sneaker> findAllByStatusIsTrueOrderByIdDesc();
}
