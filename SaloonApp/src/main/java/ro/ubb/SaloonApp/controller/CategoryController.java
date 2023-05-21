package ro.ubb.SaloonApp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.SaloonApp.dto.CategoryDto;
import ro.ubb.SaloonApp.dto.CategoryViewDto;
import ro.ubb.SaloonApp.service.CategoryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryViewDto>> readAll() {
        List<CategoryViewDto> categories = categoryService.readAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryViewDto> readById(@PathVariable Integer id) {
        CategoryViewDto category = categoryService.readById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CategoryViewDto> create(@RequestBody @Valid CategoryDto categoryDto) {
        CategoryViewDto category = categoryService.create(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryViewDto> updateById(@PathVariable Integer id, @RequestBody @Valid CategoryDto categoryDto) {
        CategoryViewDto category = categoryService.updateById(id, categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryViewDto> deleteById(@PathVariable Integer id) {
        CategoryViewDto category = categoryService.deleteById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
