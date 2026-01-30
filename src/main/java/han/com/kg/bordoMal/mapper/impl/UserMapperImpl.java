package han.com.kg.bordoMal.mapper.impl;

import han.com.kg.bordoMal.dto.request.UserRequest;
import han.com.kg.bordoMal.dto.response.UserResponse;
import han.com.kg.bordoMal.mapper.UserMapper;
import han.com.kg.bordoMal.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse tDto(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setPhone(user.getPhone());
        response.setEmail(user.getEmail());
        response.setPasswordHash(user.getPasswordHash());
        response.setStatus(user.getStatus());
        response.setRole(user.getRole());
        response.setFavorites(user.getFavorites());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return response;
    }

    @Override
    public List<UserResponse> tDtos(List<User> users) {
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(tDto(user));
        }
        return responses;
    }

    @Override
    public User toEntity(UserRequest request) {
        User user=new User();
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPasswordHash());
        return user;
    }
}
