package com.lwj.pets.tasks;

import com.lwj.pets.domain.BusinessSetting;
import com.lwj.pets.domain.BusinessVaccine;
import com.lwj.pets.service.BusinessSettingService;
import com.lwj.pets.service.BusinessVaccineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author ：焦康
 * @Date ：Created in 20:55 2025/3/3
 * @Desc ：疫苗接种提醒任务
 */
@Slf4j
@Component
public class VaccineTasks {

    @Autowired
    private BusinessSettingService businessSettingService;
    @Autowired
    private BusinessVaccineService businessVaccineService;

    private static final String VACCINE_NOTIFY = "VACCINE_NOTIFY";


    @Scheduled(fixedDelay = 5000) // 每隔5秒执行一次
    public void reportCurrentTime() {
        try {
            List<BusinessSetting> businessSettings = businessSettingService.getSpecialSetting(VACCINE_NOTIFY, "TRUE");
            if (!businessSettings.isEmpty()) {
                businessSettings.parallelStream().forEach(owner -> {
                    try {
                        List<BusinessVaccine> businessVaccineList = businessVaccineService.getVaccineRecordByOwnerId(owner.getOwnerId());
                        businessVaccineList.stream()
                                .filter(businessVaccine -> isLess7day(businessVaccine.getNextDate()))
                                .forEach(this::sendMessage);
                    } catch (Exception e) {
                        log.error("Error processing owner ID: {}", owner.getOwnerId(), e);
                    }
                });
            }
        } catch (Exception e) {
            log.error("Error in vaccine notification task", e);
        }
    }

    /**
     * 发送消息
     */
    private void sendMessage(BusinessVaccine businessVaccine) {
        log.info("Sending message for vaccine: {}, next date: {}", businessVaccine.getVaccineName(), businessVaccine.getNextDate());
    }

    /**
     * 判断是否小于7天
     */
    private boolean isLess7day(LocalDate nextDate) {
        LocalDate now = LocalDate.now();
        return now.plusDays(7).isAfter(nextDate);
    }
}
