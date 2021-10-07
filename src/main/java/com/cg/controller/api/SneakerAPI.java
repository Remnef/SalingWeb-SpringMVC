package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.exception.UserNameExistsException;
import com.cg.model.Category;
import com.cg.model.Sneaker;
import com.cg.service.Category.ICategoryService;
import com.cg.service.Sneaker.ISneakerService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/sneakers")
public class SneakerAPI {

    private AppUtils appUtils;

    @Autowired
    private ISneakerService sneakerService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Sneaker>> findAll() {
        List<Sneaker> sneakers = (List<Sneaker>) sneakerService.findAll();
        if (sneakers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sneakers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Sneaker sneaker, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        Optional<Sneaker> sneakerOptional = sneakerService.findByName(sneaker.getName());
        if (sneakerOptional.isPresent()) {
            throw new UserNameExistsException("This sneaker is already exists");
        }
        Optional<Category> category = categoryService.findById(sneaker.getCategory().getId());
        if (category.isPresent()) {
            sneaker.setCategory(category.get());
            return new ResponseEntity<>(sneakerService.save(sneaker), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Sneaker sneaker, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        Optional<Sneaker> sneakerOptional = sneakerService.findById(sneaker.getId());
        if (!sneakerOptional.isPresent()) {
            throw new DataInputException("Not found this data");
        }
        Optional<Category> category = categoryService.findById(sneaker.getCategory().getId());
        if (category.isPresent()) {
            sneaker.setCategory(category.get());
            return new ResponseEntity<>(sneakerService.save(sneaker), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getSneaker/{id}")
    public ResponseEntity<Sneaker> getSneakerById(@PathVariable long id) {
        Optional<Sneaker> sneaker = sneakerService.findById(id);
        if (sneaker.isPresent()) {
            return new ResponseEntity<>(sneaker.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Optional<Sneaker> sneakerOptional = sneakerService.findById(id);
        if (!sneakerOptional.isPresent()) {
            throw new DataInputException("Not found");
        }
        return new ResponseEntity<>(sneakerService.setDelete(sneakerOptional), HttpStatus.ACCEPTED);
    }
}
