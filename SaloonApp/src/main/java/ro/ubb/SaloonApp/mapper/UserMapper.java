package ro.ubb.SaloonApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.ubb.SaloonApp.dto.CustomerViewDto;
import ro.ubb.SaloonApp.dto.LoginViewDto;
import ro.ubb.SaloonApp.dto.UserDto;
import ro.ubb.SaloonApp.dto.EmployeeViewDto;
import ro.ubb.SaloonApp.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    LoginViewDto toLoginViewDto(User user, String token);

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    CustomerViewDto toCustomerViewDto(User user);

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    EmployeeViewDto toEmployeeViewDto(User user);

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    List<EmployeeViewDto> toEmployeeViewDtos(List<User> users);

    @Mapping(target = "role", expression = "java(null)")
    @Mapping(target = "categories", expression = "java(new java.util.HashSet<>())")
    User toEntity(UserDto userDto);
}