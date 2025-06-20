package com.example.deliverymatch.mappers;

import com.example.deliverymatch.dtos.AnnouncementDTO;
import com.example.deliverymatch.entities.Announcement;
import com.example.deliverymatch.entities.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementMapper {

//    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "driverName", source = "driver.name")
    AnnouncementDTO toDTO(Announcement announcement);

//    @Mapping(target = "driver", ignore = true)
    Announcement toEntity(AnnouncementDTO dto);

    List<AnnouncementDTO> toDTOs(List<Announcement> announcements);

//    default Driver mapDriverIdToDriver(Long driverId) {
//        if (driverId == null) {
//            return null;
//        }
//        Driver driver = new Driver();
//        driver.setId(driverId);
//        return driver;
//    }

}
