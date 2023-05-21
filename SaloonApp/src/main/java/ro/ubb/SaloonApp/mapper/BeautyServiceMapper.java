package ro.ubb.SaloonApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.ubb.SaloonApp.dto.BeautyServiceDto;
import ro.ubb.SaloonApp.dto.BeautyServiceViewDto;
import ro.ubb.SaloonApp.model.BeautyService;

import java.util.List;

@Mapper
public interface BeautyServiceMapper {
    BeautyServiceMapper INSTANCE = Mappers.getMapper(BeautyServiceMapper.class);

    BeautyServiceDto toBeautyServiceDto(BeautyService beautyService);

    BeautyService toEntity(BeautyServiceDto beautyServiceDto);
    List<BeautyServiceViewDto> toBeautyServiceDtos(List<BeautyService> beautyServices);

    BeautyServiceViewDto toBeautyServiceViewDto(BeautyService beautyServiceToBeUpdated);
}