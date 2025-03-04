package com.lwj.pets.res.deworm;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DewormRecord {
    private Integer id;
    private Integer petId;
    private String petName;
    private String dewormType;
    private String productName;
    private BigDecimal metering;
    private BigDecimal weight;
    private LocalDate dewormDate;
    private LocalDate nextDate;
    private String address;
    private String notes;
    private Integer gap;
}
