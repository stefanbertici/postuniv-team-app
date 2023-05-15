package ro.ubb.SaloonApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.ubb.SaloonApp.dto.UserDto;
import ro.ubb.SaloonApp.dto.UserViewDto;
import ro.ubb.SaloonApp.model.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role", expression = "java(java.util.Optional.ofNullable(user.getRole().getName()).orElse(null))")
    UserViewDto toViewDto(User user);

    @Mapping(target = "role", expression = "java(null)")
    User toEntity(UserDto userDto);
}
