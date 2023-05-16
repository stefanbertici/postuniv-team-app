package ro.ubb.SaloonApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.ubb.SaloonApp.dto.CustomerViewDto;
import ro.ubb.SaloonApp.dto.UserDto;
import ro.ubb.SaloonApp.dto.UserViewDto;
import ro.ubb.SaloonApp.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role", expression = "java(user.getRole().value)")
    CustomerViewDto toCustomerViewDto(User user);

    @Mapping(target = "role", expression = "java(user.getRole().value)")
    @Mapping(target = "categories", expression = "java(new java.util.HashSet<>())")
    UserViewDto toUserViewDto(User user);

    @Mapping(target = "role", expression = "java(user.getRole().value)")
    @Mapping(target = "categories", expression = "java(new java.util.HashSet<>())")
    List<UserViewDto> toUserViewDtos(List<User> users);

    @Mapping(target = "role", expression = "java(null)")
    @Mapping(target = "categories", expression = "java(new java.util.HashSet<>())")
    User toEntity(UserDto userDto);
}
