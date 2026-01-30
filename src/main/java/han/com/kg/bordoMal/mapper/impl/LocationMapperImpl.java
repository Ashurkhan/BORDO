package han.com.kg.bordoMal.mapper.impl;

import han.com.kg.bordoMal.dto.request.LocationRequest;
import han.com.kg.bordoMal.dto.response.LocationResponse;
import han.com.kg.bordoMal.mapper.LocationMapper;
import han.com.kg.bordoMal.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class LocationMapperImpl implements LocationMapper {
    @Override
    public LocationResponse tDto(Location location) {
        LocationResponse response =new LocationResponse();
        response.setId(location.getId());
        response.setStreet(location.getStreet());
        response.setVillage(location.getVillage());
        response.setCity(location.getCity());
        response.setRegion(location.getRegion());
        response.setCountry(location.getCountry());
        return response;
    }

    @Override
    public List<LocationResponse> tDtos(List<Location> locations) {
        List<LocationResponse> responses=new ArrayList<>();
        for(Location location:locations){
            responses.add(tDto(location));
        }
        return responses;
    }

    @Override
    public Location toEntity(LocationRequest request) {
        Location location=new Location();
        location.setStreet(location.getStreet());
        location.setVillage(location.getVillage());
        location.setCity(location.getCity());
        location.setRegion(location.getRegion());
        location.setCountry(location.getCountry());
        return location;
    }
}
