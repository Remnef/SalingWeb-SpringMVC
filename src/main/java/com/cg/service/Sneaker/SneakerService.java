package com.cg.service.Sneaker;

import com.cg.model.Category;
import com.cg.model.Sneaker;
import com.cg.repository.ICategoryRepository;
import com.cg.repository.ISneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SneakerService implements ISneakerService {

    @Autowired
    private ISneakerRepository sneakerRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

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
        return sneakerRepository.findTopByStatusIsTrueOrderByIdDesc();
    }

    @Override
    public Iterable<Sneaker> findAllByStatusIsTrue() {
        return sneakerRepository.findAllByStatusIsTrue();
    }

    @Override
    public Iterable<Sneaker> findAllByOrderByIdDesc() {
        return sneakerRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Iterable<Sneaker> findAllByCategoryAndStatusIsTrue(Long id) {
        return sneakerRepository.findAllByCategoryAndStatusIsTrue(categoryRepository.findById(id).get());
    }

    @Override
    public Iterable<Sneaker> findByKeyword(String keyword) {
        return sneakerRepository.findByNameContaining(keyword);
    }

    @Override
    public Optional<Sneaker> findByName(String name) {
        return sneakerRepository.findByName(name);
    }

    @Override
    public Sneaker setDelete(Optional<Sneaker> sneakerOptional) {
        Sneaker sneaker = sneakerOptional.get();
        sneaker.setStatus(sneaker.isStatus() ? false : true);
        sneakerRepository.save(sneaker);
        return sneaker;
    }

    @Override
    public Iterable<Sneaker> findAllByStatusIsTrueOrderByIdDesc() {
        return sneakerRepository.findAllByStatusIsTrueOrderByIdDesc();
    }
}
