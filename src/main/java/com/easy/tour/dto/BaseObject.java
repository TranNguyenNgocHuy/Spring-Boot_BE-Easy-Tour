package com.easy.tour.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


@MappedSuperclass
@Getter
@Setter
public class BaseObject implements Serializable {
}
