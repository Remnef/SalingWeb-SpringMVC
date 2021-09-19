package com.cg.service.Sneaker;

import com.cg.model.Sneaker;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ISneakerService extends IGeneralService<Sneaker> {
    Optional<Sneaker> findTopByOrderByIdDesc();
}
