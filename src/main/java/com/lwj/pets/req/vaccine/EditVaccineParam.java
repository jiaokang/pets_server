package com.lwj.pets.req.vaccine;


import lombok.Data;

import java.time.LocalDate;

@Data
public class EditVaccineParam extends AddVaccineParam{
    private Integer id;
}
