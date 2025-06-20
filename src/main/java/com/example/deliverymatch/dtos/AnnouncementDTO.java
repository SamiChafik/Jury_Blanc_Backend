package com.example.deliverymatch.dtos;

import com.example.deliverymatch.entities.AnnoncementStatus;
import com.example.deliverymatch.entities.Driver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
    private Long id;
    private String departure;
    private String final_destination;
    private List<String> stages;
    private String maximum_dimensions;
    private String goodsType;
    private String capacity;
    private LocalDate date;
    private AnnoncementStatus status;
//    private Long driverId;
    private String driverName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getFinal_destination() {
        return final_destination;
    }

    public void setFinal_destination(String final_destination) {
        this.final_destination = final_destination;
    }

    public List<String> getStages() {
        return stages;
    }

    public void setStages(List<String> stages) {
        this.stages = stages;
    }

    public String getMaximum_dimensions() {
        return maximum_dimensions;
    }

    public void setMaximum_dimensions(String maximum_dimensions) {
        this.maximum_dimensions = maximum_dimensions;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AnnoncementStatus getStatus() {
        return status;
    }

    public void setStatus(AnnoncementStatus status) {
        this.status = status;
    }

//    public Long getDriverId() {
//        return driverId;
//    }
//
//    public void setDriverId(Long driverId) {
//        this.driverId = driverId;
//    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
