package com.example.deliverymatch.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String departure;
    private String final_destination;

    @ElementCollection
    private List<String> stages;

    private String maximum_dimensions;
    private String goodsType;
    private String capacity;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private AnnoncementStatus status;

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driverId")
    private Driver driver;

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

    public void setGoodsType(String goods_type) {
        this.goodsType = goods_type;
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

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

}
