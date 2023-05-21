package ro.ubb.SaloonApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.ubb.SaloonApp.dto.CategoryDto;
import ro.ubb.SaloonApp.dto.CategoryViewDto;
import ro.ubb.SaloonApp.model.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryViewDto toCategoryViewDto(Category category);

    List<CategoryViewDto> toCategoryViewDtos(List<Category> categories);

    Category toEntity(CategoryDto categoryDto);
}