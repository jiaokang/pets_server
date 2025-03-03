package com.lwj.pets.res.vaccine;


import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineRecord {
    private Integer id;
    private Integer petId;
    private String petName;
    private String vaccineName;
    private LocalDate vaccineDate;
    private LocalDate nextDate;
    private String hospital;
    private String notes;
    private Integer gap;
}
