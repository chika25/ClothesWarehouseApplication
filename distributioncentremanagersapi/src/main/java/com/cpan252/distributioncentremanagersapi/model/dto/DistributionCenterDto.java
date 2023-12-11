package com.cpan252.distributioncentremanagersapi.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DistributionCenterDto {
    private Long id;
    private String name;
    private List<ItemDto> itemsAvailable;
    private double latitude;
    private double longitude;

    // Default constructor (needed for Jackson)
    public DistributionCenterDto() {
    }

    // Explicitly defined constructor
    public DistributionCenterDto(Long id, String name, List<ItemDto> itemsAvailable, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.itemsAvailable = itemsAvailable;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }

    public List<ItemDto> getItemsAvailable(){
            return itemsAvailable;
    }

    @Override
    public String toString() {
        return "DistributionCenterDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemsAvailable=" + itemsAvailable +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}