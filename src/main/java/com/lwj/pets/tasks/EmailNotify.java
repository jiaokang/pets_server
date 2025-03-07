package com.lwj.pets.tasks;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author ：焦康
 * @Date ：Created in 21:41 2025/3/7
 * @Desc ：
 */
@Data
public class EmailNotify {
    private String email;
    private String petName;
    private String petOwnerName;
    private String date;
    private String eventType;
    /**
     * 下次接种日期
     */
    private LocalDate nextDate;
}
