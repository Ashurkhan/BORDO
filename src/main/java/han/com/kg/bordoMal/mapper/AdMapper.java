package han.com.kg.bordoMal.mapper;

import han.com.kg.bordoMal.dto.request.AdRequest;
import han.com.kg.bordoMal.dto.response.AdResponse;
import han.com.kg.bordoMal.model.Ad;

import java.util.List;

public interface AdMapper {
    AdResponse tDto(Ad ad);
    List<AdResponse> tDtos(List<Ad> ads);
    Ad toEntity(AdRequest reqeust);
}
