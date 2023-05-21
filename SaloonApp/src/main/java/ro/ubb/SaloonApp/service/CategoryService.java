package ro.ubb.SaloonApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.SaloonApp.dto.CategoryDto;
import ro.ubb.SaloonApp.dto.CategoryViewDto;
import ro.ubb.SaloonApp.mapper.CategoryMapper;
import ro.ubb.SaloonApp.model.Category;
import ro.ubb.SaloonApp.repository.CategoryRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final RepositoryChecker repositoryChecker;
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryViewDto> readAll() {
        List<Category> categories = categoryRepository.findAll();

        return CategoryMapper.INSTANCE.toCategoryViewDtos(categories);
    }

    @Transactional
    public CategoryViewDto readById(int id) {
        Category category = repositoryChecker.getCategoryIfExists(id);

        return CategoryMapper.INSTANCE.toCategoryViewDto(category);
    }

    @Transactional
    public CategoryViewDto create(CategoryDto categoryDto) {
        repositoryChecker.checkIfCategoryAlreadyExists(categoryDto.name());

        Category category = CategoryMapper.INSTANCE.toEntity(categoryDto);
        categoryRepository.save(category);

        return CategoryMapper.INSTANCE.toCategoryViewDto(category);
    }

    @Transactional
    public CategoryViewDto updateById(Integer id, CategoryDto categoryDto) {
        Category category = repositoryChecker.getCategoryIfExists(id);

        category.setName(categoryDto.name());

        return CategoryMapper.INSTANCE.toCategoryViewDto(category);
    }

    @Transactional
    public CategoryViewDto deleteById(Integer id) {
        Category category = repositoryChecker.getCategoryIfExists(id);

        category.clearUsers();
        categoryRepository.deleteById(id);

        return CategoryMapper.INSTANCE.toCategoryViewDto(category);
    }
}
