package com.example.deliverymatch.dtos;

import com.example.deliverymatch.entities.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class RequestDTO {
    private Long id;
    private String dimensions;
    private String weight;
    private String type;
    private RequestStatus status;
    private Long announcementId;
//    private Long senderId;


    public RequestDTO(Long id, String dimensions, String weight, String type, RequestStatus status, Long announcementId) {
        this.id = id;
        this.dimensions = dimensions;
        this.weight = weight;
        this.type = type;
        this.status = status;
        this.announcementId = announcementId;
    }

    public RequestDTO(RequestStatus status) {
        this.status = status;
    }

    public RequestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Long getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Long announcementId) {
        this.announcementId = announcementId;
    }

//    public Long getSenderId() {
//        return senderId;
//    }
//
//    public void setSenderId(Long senderId) {
//        this.senderId = senderId;
//    }
}
