//package ro.ubb.SaloonApp.controller;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ro.ubb.SaloonApp.dto.BeautyServiceDto;
//import ro.ubb.SaloonApp.dto.BeautyServiceResponse;
//import ro.ubb.SaloonApp.dto.BeautyServiceViewDto;
//import ro.ubb.SaloonApp.exception.ServiceException;
//import ro.ubb.SaloonApp.service.BeautyServiceService;
//
//import java.util.List;
//import java.util.Set;
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("/beautyServices")
//public class BeautyServiceController {
//    private final BeautyServiceService beautyService;
//
//    @GetMapping("/readAllBeautyServices")
//    public ResponseEntity<List<BeautyServiceViewDto>> readAllBeautyServices() {
//        List<BeautyServiceViewDto> beautyServiceDtos = beautyService.readAllBeautyServices();
//
//        return new ResponseEntity<>(beautyServiceDtos, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BeautyServiceViewDto> readById(@PathVariable Integer id) {
//        BeautyServiceViewDto beautyServiceDto = beautyService.readById(id);
//
//        return new ResponseEntity<>(beautyServiceDto, HttpStatus.OK);
//    }
//
//    @PostMapping("/save")
//    ResponseEntity<BeautyServiceResponse> saveBeautyService(@RequestBody BeautyServiceDto beautyServiceDto) {
//        try {
//            BeautyServiceDto beautyServiceSaved = beautyService.saveBeautyService(beautyServiceDto);
//
//            BeautyServiceResponse beautyServiceResponse = new BeautyServiceResponse();
//            beautyServiceResponse.setBeautyServices(Set.of(beautyServiceSaved));
//
//            return new ResponseEntity<>(beautyServiceResponse, HttpStatus.OK);
//        } catch (ServiceException e) {
//            BeautyServiceResponse beautyServiceResponse = new BeautyServiceResponse();
//            beautyServiceResponse.setErrorMessage(e.getMessage());
//
//            return new ResponseEntity<>(beautyServiceResponse, HttpStatus.OK);
//        }
//    }
//
//    @PutMapping("/{id}")
//    ResponseEntity<BeautyServiceViewDto> updateBeautyService(@PathVariable Integer id, @RequestBody BeautyServiceDto beautyServiceDto) {
//        BeautyServiceViewDto beautyServiceSaved = beautyService.updateBeautyService(id, beautyServiceDto);
//
//        return new ResponseEntity<>(beautyServiceSaved, HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
//    ResponseEntity<BeautyServiceViewDto> deleteBeautyService(@PathVariable Integer id) {
//        BeautyServiceViewDto deletedBeautyService = beautyService.deleteBeautyService(id);
//
//        return new ResponseEntity<>(deletedBeautyService, HttpStatus.OK);
//    }
//}