package ro.ubb.SaloonApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ro.ubb.SaloonApp.dto.BeautyServiceDto;
import ro.ubb.SaloonApp.dto.BeautyServiceViewDto;
import ro.ubb.SaloonApp.mapper.BeautyServiceMapper;
import ro.ubb.SaloonApp.model.BeautyService;
import ro.ubb.SaloonApp.model.Category;
import ro.ubb.SaloonApp.repository.BeautyServiceRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.util.List;

@AllArgsConstructor
@Service
public class BeautyServiceService {
    private final RepositoryChecker repositoryChecker;
    private final BeautyServiceRepository beautyServiceRepository;

    public List<BeautyServiceViewDto> readAllBeautyServices() {
        List<BeautyService> beautyServices = beautyServiceRepository.findAll();

        return BeautyServiceMapper.INSTANCE.toBeautyServiceDtos(beautyServices);
    }

    public BeautyServiceViewDto readById(Integer id) {
        BeautyService foundBeautyService = repositoryChecker.getBeautyServiceIfExists(id);

        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(foundBeautyService);
    }

    public BeautyServiceViewDto saveBeautyService(@RequestBody BeautyServiceDto beautyServiceDto
    ) {
        BeautyService beautyServiceToBeSaved = BeautyServiceMapper.INSTANCE.toEntity(beautyServiceDto);

        String categoryName = beautyServiceDto.categoryName();
        Category categoryOfSavedBeautyService = repositoryChecker.getCategoryByName(categoryName);

        beautyServiceToBeSaved.setCategory(categoryOfSavedBeautyService);

        BeautyService beautyServiceSaved = beautyServiceRepository.save(beautyServiceToBeSaved);

        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(beautyServiceSaved);
    }

    @Transactional
    public BeautyServiceViewDto updateBeautyService(Integer id, BeautyServiceDto beautyServiceDto) {
        String categoryName = beautyServiceDto.categoryName();
        Category categoryOfUpdatedBeautyService = repositoryChecker.getCategoryByName(categoryName);

        BeautyService beautyServiceToBeUpdated = repositoryChecker.getBeautyServiceIfExists(id);

        beautyServiceToBeUpdated.setCategory(categoryOfUpdatedBeautyService);
        beautyServiceToBeUpdated.setName(beautyServiceDto.name());
        beautyServiceToBeUpdated.setPrice(beautyServiceDto.price());
        beautyServiceToBeUpdated.setDuration(beautyServiceDto.duration());

        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(beautyServiceToBeUpdated);
    }

    public BeautyServiceViewDto deleteBeautyService(Integer id) {
        BeautyService deletedBeautyService = repositoryChecker.getBeautyServiceIfExists(id);

        beautyServiceRepository.deleteById(id);

        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(deletedBeautyService);
    }
}
