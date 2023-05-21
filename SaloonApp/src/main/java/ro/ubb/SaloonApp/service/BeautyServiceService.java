package ro.ubb.SaloonApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import ro.ubb.SaloonApp.dto.BeautyServiceDto;
import ro.ubb.SaloonApp.dto.BeautyServiceViewDto;
import ro.ubb.SaloonApp.exception.ResourceNotFoundException;
import ro.ubb.SaloonApp.exception.ServiceException;
import ro.ubb.SaloonApp.mapper.BeautyServiceMapper;
import ro.ubb.SaloonApp.model.BeautyService;
import ro.ubb.SaloonApp.model.Category;
import ro.ubb.SaloonApp.repository.BeautyServiceRepository;
import ro.ubb.SaloonApp.utils.RepositoryChecker;

import java.util.List;
import java.util.Optional;

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
        Optional<BeautyService> beautyServiceOptional = beautyServiceRepository.findById(id);
        BeautyService beautyService = beautyServiceOptional.orElseThrow(() -> new ServiceException("Entity does not exist"));

        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(beautyService);
    }

    public BeautyServiceViewDto saveBeautyService(@RequestBody BeautyServiceDto beautyServiceDto
    ) {
        BeautyService beautyServiceToBeSaved = BeautyServiceMapper.INSTANCE.toEntity(beautyServiceDto);

        String categoryName = beautyServiceDto.categoryName();
        Category categoryOfSavedBeautySerivce = repositoryChecker.getCategoryByName(categoryName);

        beautyServiceToBeSaved.setCategory(categoryOfSavedBeautySerivce);

        BeautyService beautyServiceSaved = beautyServiceRepository.save(beautyServiceToBeSaved);

        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(beautyServiceSaved);
    }

    @Transactional
    public BeautyServiceViewDto updateBeautyService(Integer id, BeautyServiceDto beautyServiceDto) {
        Optional<BeautyService> optionalBeautyService = beautyServiceRepository.findById(id);
        if (optionalBeautyService.isEmpty()) {
            throw new ResourceNotFoundException("The entity with id = " + id + " does not exist");
        }

        String categoryName = beautyServiceDto.categoryName();
        Category categoryOfUpdatedBeautyService = repositoryChecker.getCategoryByName(categoryName);

        BeautyService beautyServiceToBeUpdated = optionalBeautyService.get();

        beautyServiceToBeUpdated.setCategory(categoryOfUpdatedBeautyService);
        beautyServiceToBeUpdated.setName(beautyServiceDto.name());
        beautyServiceToBeUpdated.setPrice(beautyServiceDto.price());
        beautyServiceToBeUpdated.setDuration(beautyServiceDto.duration());

        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(beautyServiceToBeUpdated);
    }

    public BeautyServiceViewDto deleteBeautyService(Integer id) {
        Optional<BeautyService> optionalBeautyService = beautyServiceRepository.findById(id);
        if (optionalBeautyService.isEmpty()) {
            throw new ResourceNotFoundException("The entity with id = " + id + " does not exist");
        }

        beautyServiceRepository.deleteById(id);

        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(optionalBeautyService.get());
    }
}
