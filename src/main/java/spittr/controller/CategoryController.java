package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spittr.entity.Category;
import spittr.repo.CategoryRepo;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/test")
    public List<Category> test(){
        return categoryRepo.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if(category.getId() != null && category.getId() != 0)
            return new ResponseEntity("Redundant param: id must be null", HttpStatus.NOT_ACCEPTABLE);
        if(category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("Missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepo.save(category));
    }






}
