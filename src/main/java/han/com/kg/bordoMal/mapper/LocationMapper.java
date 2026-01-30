package han.com.kg.bordoMal.mapper;

import han.com.kg.bordoMal.dto.request.LocationRequest;
import han.com.kg.bordoMal.dto.response.LocationResponse;
import han.com.kg.bordoMal.model.Location;

import java.util.List;

public interface LocationMapper {
    LocationResponse tDto(Location location);
    List<LocationResponse> tDtos(List<Location> locations);
    Location toEntity(LocationRequest request );
}
