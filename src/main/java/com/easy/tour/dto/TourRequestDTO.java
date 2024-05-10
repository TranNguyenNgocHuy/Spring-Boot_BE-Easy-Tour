package com.easy.tour.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class TourRequestDTO {
    private UUID uuid;

    private String description;

    private String createdBy;

    private Date createdDate;
}
