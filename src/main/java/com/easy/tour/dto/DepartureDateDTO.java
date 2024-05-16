package com.easy.tour.dto;

import com.easy.tour.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class DepartureDateDTO extends BaseEntity {
    String tourCode;
    LocalDate departureDate;
}
