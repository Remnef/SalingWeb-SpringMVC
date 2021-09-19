package com.cg.service.Sneaker;

import com.cg.model.Sneaker;
import com.cg.repository.ISneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SneakerService implements ISneakerService{

    @Autowired
    private ISneakerRepository sneakerRepository;

    @Override
    public Iterable<Sneaker> findAll() {
        return sneakerRepository.findAll();
    }

    @Override
    public Optional<Sneaker> findById(Long id) {
        return sneakerRepository.findById(id);
    }

    @Override
    public Sneaker save(Sneaker sneaker) {
        return sneakerRepository.save(sneaker);
    }

    @Override
    public void deleteById(Long id) {
        sneakerRepository.deleteById(id);
    }

    @Override
    public Optional<Sneaker> findTopByOrderByIdDesc() {
        return sneakerRepository.findTopByOrderByIdDesc();
    }
}
