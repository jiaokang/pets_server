package com.lwj.pets.tasks;

import com.lwj.pets.common.SettingItemEnum;
import com.lwj.pets.domain.BusinessSetting;
import com.lwj.pets.service.BusinessDewormingService;
import com.lwj.pets.service.BusinessSettingService;
import com.lwj.pets.service.BusinessVaccineService;
import com.lwj.pets.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author ：焦康
 * @Date ：Created in 20:55 2025/3/3
 * @Desc ：驱虫接种提醒任务
 */
@Slf4j
@Component
public class DewormingTasks {

    @Autowired
    private BusinessSettingService businessSettingService;
    @Autowired
    private BusinessDewormingService businessDewormingService;
    @Autowired
    EmailService emailService;


    @Scheduled(cron = "0 0 9-18 * * ?") // 每天9点到18点执行
    public void reportCurrentTime() {
        try {
            // 获取设置项目中所有打开了驱虫提醒的用户
            List<BusinessSetting> businessSettings = businessSettingService.getSpecialSetting(SettingItemEnum.DEWORMING_NOTIFY.getCode(), "true");

            if (!businessSettings.isEmpty()) {
                businessSettings.parallelStream().forEach(owner -> {
                    try {
                        List<EmailNotify> emailNotifyList = businessDewormingService.getDewormingNotifyByOwnerId(owner.getOwnerId());
                        emailNotifyList.stream()
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
    private void sendMessage(EmailNotify businessVaccine) {
        try {
            emailService.sendNotifyEmail(businessVaccine.getEmail(), businessVaccine.getEventType(), businessVaccine.getPetOwnerName(),
                    businessVaccine.getPetName(), businessVaccine.getDate());
        } catch (MessagingException e) {
            log.error("Error sending email to owner email: {}", businessVaccine.getEmail(), e);
        }
    }

    /**
     * 判断是否小于7天
     */
    private boolean isLess7day(LocalDate nextDate) {
        LocalDate now = LocalDate.now();
        return now.plusDays(7).isAfter(nextDate);
    }
}
