package com.lwj.pets.req.deworm;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EditDewormParam extends AddDewormParam{
    private Integer id;
}
