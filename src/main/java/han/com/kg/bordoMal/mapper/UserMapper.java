package han.com.kg.bordoMal.mapper;

import han.com.kg.bordoMal.dto.request.UserRequest;
import han.com.kg.bordoMal.dto.response.UserResponse;
import han.com.kg.bordoMal.model.User;

import java.util.List;

public interface UserMapper {
    UserResponse tDto(User user);
    List<UserResponse> tDtos(List<User> users);
    User toEntity (UserRequest request);
}
