package com.lwj.pets.req.deworm;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AddDewormParam {
    private Integer petId;
    private String dewormType;
    private String productName;
    private BigDecimal metering;
    private LocalDate dewormDate;
    private Integer gap;
    private BigDecimal weight;
    private String address;
    private String notes;
}
