package han.com.kg.bordoMal.mapper.impl;

import han.com.kg.bordoMal.dto.response.ImageResponse;
import han.com.kg.bordoMal.mapper.ImageMapper;
import han.com.kg.bordoMal.model.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapperImpl implements ImageMapper {

    @Override
    public ImageResponse tDto(Image image) {
        if (image == null) return null;

        ImageResponse response = new ImageResponse();
        response.setId(image.getId());
        response.setUrl(image.getUrl());
        response.setMain(image.isMain());
        response.setCreatedAt(image.getCreatedAt());

        return response;
    }
}
