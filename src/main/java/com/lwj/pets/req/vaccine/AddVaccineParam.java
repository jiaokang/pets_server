package com.lwj.pets.req.vaccine;


import lombok.Data;

import java.time.LocalDate;

@Data
public class AddVaccineParam {
    private Integer petId;
    private String vaccineName;
    private LocalDate vaccineDate;
    private Integer gap;
    private String hospital;
    private String notes;
}
