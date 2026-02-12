package han.com.kg.bordoMal.mapper;

import han.com.kg.bordoMal.dto.response.ImageResponse;
import han.com.kg.bordoMal.model.Image;

public interface ImageMapper {

    ImageResponse tDto(Image image);
}
