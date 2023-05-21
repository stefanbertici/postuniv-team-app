//package ro.ubb.SaloonApp.service;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.RequestBody;
//import ro.ubb.SaloonApp.dto.BeautyServiceDto;
//import ro.ubb.SaloonApp.dto.BeautyServiceViewDto;
//import ro.ubb.SaloonApp.exception.ResourceNotFoundException;
//import ro.ubb.SaloonApp.exception.ServiceException;
//import ro.ubb.SaloonApp.mapper.BeautyServiceMapper;
//import ro.ubb.SaloonApp.model.BeautyService;
//import ro.ubb.SaloonApp.model.Category;
//import ro.ubb.SaloonApp.repository.BeautyServiceRepository;
//import ro.ubb.SaloonApp.repository.CategoryRepository;
//import ro.ubb.SaloonApp.utils.RepositoryChecker;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//@AllArgsConstructor
//@Service
//public class BeautyServiceService {
//    private final RepositoryChecker repositoryChecker;
//    private final BeautyServiceRepository beautyServiceRepository;
//    private final CategoryRepository categoryRepository;
//
//    public List<BeautyServiceViewDto> readAllBeautyServices() {
//        List<BeautyService> beautyServices = beautyServiceRepository.findAll();
//
//        return BeautyServiceMapper.INSTANCE.toBeautyServiceDtos(beautyServices);
//    }
//
//    public BeautyServiceViewDto readById(Integer id) {
//        Optional<BeautyService> beautyServiceOptional = beautyServiceRepository.findById(id);
//        BeautyService beautyService = beautyServiceOptional.orElseThrow(() -> new ServiceException("Entity does not exist"));
//
//        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(beautyService);
//    }
//
//    public BeautyServiceDto saveBeautyService(@RequestBody BeautyServiceDto portfolioDto) {
//        BeautyService beautyServiceToBeSaved = BeautyServiceMapper.INSTANCE.toEntity(portfolioDto);
//
//        BeautyService beautyServiceSaved = beautyServiceRepository.save(beautyServiceToBeSaved);
//
//        return BeautyServiceMapper.INSTANCE.toBeautyServiceDto(beautyServiceSaved);
//    }
//
//    @Transactional
//    public BeautyServiceViewDto updateBeautyService(Integer id, BeautyServiceDto beautyServiceFromRequestDto) {
//        Optional<BeautyService> optionalBeautyService = beautyServiceRepository.findById(id);
//        if (optionalBeautyService.isEmpty()) {
//            throw new ResourceNotFoundException("The entity with id = " + id + " does not exist");
//        }
//
//        BeautyService beautyServiceToBeUpdated = optionalBeautyService.get();
//
//        beautyServiceToBeUpdated.setCategories(beautyServiceFromRequestDto.getCategory());
//
//        Optional<Category> categoryOptional=categoryRepository.findById(beautyServiceFromRequestDto.getCategory().getId());
//        Set<Category> category=categoryOptional.get();
//
//        beautyServiceToBeUpdated.setCategories(category);
//
//        beautyServiceToBeUpdated.setName(beautyServiceFromRequestDto.getName());
//        beautyServiceToBeUpdated.setPrice(beautyServiceFromRequestDto.getPrice());
//        beautyServiceToBeUpdated.setDuration(beautyServiceFromRequestDto.getDuration());
//
//        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(beautyServiceToBeUpdated);
//    }
//
//    public BeautyServiceViewDto deleteBeautyService(Integer id) {
//        Optional<BeautyService> optionalBeautyService = beautyServiceRepository.findById(id);
//        if (optionalBeautyService.isEmpty()) {
//            throw new ResourceNotFoundException("The entity with id = " + id + " does not exist");
//        }
//
//        beautyServiceRepository.deleteById(id);
//
//        return BeautyServiceMapper.INSTANCE.toBeautyServiceViewDto(optionalBeautyService.get());
//    }
//}
