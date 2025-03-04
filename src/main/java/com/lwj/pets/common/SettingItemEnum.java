package com.lwj.pets.common;

/**
 * @Author ：焦康
 * @Date ：Created in 20:45 2025/3/4
 * @Desc ：
 */
public enum SettingItemEnum {


    VACCINE_NOTIFY("疫苗接种提醒", "VACCINE_NOTIFY","false"),
    DEWORMING_NOTIFY("驱虫提醒", "DEWORMING_NOTIFY","false"),
    DARK_MODE("深色模式", "DARK_MODE","false"),


    ;

    private String name;

    private String code;

    private String value;



    private SettingItemEnum(String name, String code, String value) {
        this.name = name;
        this.code = code;
        this.value = value;
    }







}
