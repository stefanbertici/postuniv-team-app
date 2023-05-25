package ro.ubb.SaloonApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.ubb.SaloonApp.dto.BeautyServiceDto;
import ro.ubb.SaloonApp.dto.BeautyServiceViewDto;
import ro.ubb.SaloonApp.model.BeautyService;

import java.util.List;

@Mapper
public interface BeautyServiceMapper {

    BeautyServiceMapper INSTANCE = Mappers.getMapper(BeautyServiceMapper.class);

    BeautyService toEntity(BeautyServiceDto beautyServiceDto);

    List<BeautyServiceViewDto> toBeautyServiceDtos(List<BeautyService> beautyServices);

    @Mapping(target = "categoryName", expression = "java(beautyService.getCategory().getName())")
    @Mapping(target = "durationInMinutes", expression = "java(beautyService.getDuration()*beautyService.getNumberOfAvailabilityBlock())")
    BeautyServiceViewDto toBeautyServiceViewDto(BeautyService beautyService);
}