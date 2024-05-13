package com.easy.tour.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class TourDTO extends BaseObject{
    private String tourCode;
    private String tourName;
    private String description;
    private Integer maximumSize;
    String tourRequestCode;
    private String tourImg1;
    private String tourImg2;
    private BigDecimal adult;
    private BigDecimal children;
}
