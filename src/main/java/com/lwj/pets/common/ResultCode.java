package com.lwj.pets.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应码枚举
 * <p>
 * 参考阿里巴巴开发手册响应码规范
 *
 * @author Ray.Hao
 * @since 2020/6/23
 **/
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode, Serializable {

    SUCCESS("00000", "一切ok"),

    /** 一级宏观错误码  */
    USER_ERROR("A0001", "用户端错误"),

    /** 二级宏观错误码  */
    USER_REGISTRATION_ERROR("A0100", "用户注册错误"),
    USER_NOT_AGREE_PRIVACY_AGREEMENT("A0101", "用户未同意隐私协议"),
    REGISTRATION_COUNTRY_OR_REGION_RESTRICTED("A0102", "注册国家或地区受限"),

    USERNAME_VERIFICATION_FAILED("A0110", "用户名校验失败"),
    USERNAME_ALREADY_EXISTS("A0111", "用户名已存在"),
    USERNAME_CONTAINS_SENSITIVE_WORDS("A0112", "用户名包含敏感词"),
    USERNAME_CONTAINS_SPECIAL_CHARACTERS("A0113", "用户名包含特殊字符"),

    PASSWORD_VERIFICATION_FAILED("A0120", "密码校验失败"),
    PASSWORD_LENGTH_NOT_ENOUGH("A0121", "密码长度不够"),
    PASSWORD_STRENGTH_NOT_ENOUGH("A0122", "密码强度不够"),

    VERIFICATION_CODE_INPUT_ERROR("A0130", "校验码输入错误"),
    SMS_VERIFICATION_CODE_INPUT_ERROR("A0131", "短信校验码输入错误"),
    EMAIL_VERIFICATION_CODE_INPUT_ERROR("A0132", "邮件校验码输入错误"),
    VOICE_VERIFICATION_CODE_INPUT_ERROR("A0133", "语音校验码输入错误"),

    USER_CERTIFICATE_EXCEPTION("A0140", "用户证件异常"),
    USER_CERTIFICATE_TYPE_NOT_SELECTED("A0141", "用户证件类型未选择"),
    MAINLAND_ID_NUMBER_VERIFICATION_ILLEGAL("A0142", "大陆身份证编号校验非法"),

    USER_BASIC_INFORMATION_VERIFICATION_FAILED("A0150", "用户基本信息校验失败"),
    PHONE_FORMAT_VERIFICATION_FAILED("A0151", "手机格式校验失败"),
    ADDRESS_FORMAT_VERIFICATION_FAILED("A0152", "地址格式校验失败"),
    EMAIL_FORMAT_VERIFICATION_FAILED("A0153", "邮箱格式校验失败"),

    /** 二级宏观错误码  */
    USER_LOGIN_EXCEPTION("A0200", "用户登录异常"),
    USER_ACCOUNT_FROZEN("A0201", "用户账户被冻结"),
    USER_ACCOUNT_ABOLISHED("A0202", "用户账户已作废"),

    USER_PASSWORD_ERROR("A0210", "用户名或密码错误"),
    USER_INPUT_PASSWORD_ERROR_LIMIT_EXCEEDED("A0211", "用户输入密码错误次数超限"),

    USER_IDENTITY_VERIFICATION_FAILED("A0220", "用户身份校验失败"),
    USER_FINGERPRINT_RECOGNITION_FAILED("A0221", "用户指纹识别失败"),
    USER_FACE_RECOGNITION_FAILED("A0222", "用户面容识别失败"),
    USER_NOT_AUTHORIZED_THIRD_PARTY_LOGIN("A0223", "用户未获得第三方登录授权"),

    ACCESS_TOKEN_INVALID("A0230", "访问令牌无效或已过期"),
    REFRESH_TOKEN_INVALID("A0231", "刷新令牌无效或已过期"),

    // 验证码错误
    USER_VERIFICATION_CODE_ERROR("A0240", "用户验证码错误"),
    USER_VERIFICATION_CODE_ATTEMPT_LIMIT_EXCEEDED("A0241", "用户验证码尝试次数超限"),
    USER_VERIFICATION_CODE_EXPIRED("A0242", "用户验证码过期"),

    /** 二级宏观错误码  */
    ACCESS_PERMISSION_EXCEPTION("A0300", "访问权限异常"),
    ACCESS_UNAUTHORIZED("A0301", "访问未授权"),
    AUTHORIZATION_IN_PROGRESS("A0302", "正在授权中"),
    USER_AUTHORIZATION_APPLICATION_REJECTED("A0303", "用户授权申请被拒绝"),

    ACCESS_OBJECT_PRIVACY_SETTINGS_BLOCKED("A0310", "因访问对象隐私设置被拦截"),
    AUTHORIZATION_EXPIRED("A0311", "授权已过期"),
    NO_PERMISSION_TO_USE_API("A0312", "无权限使用 API"),

    USER_ACCESS_BLOCKED("A0320", "用户访问被拦截"),
    BLACKLISTED_USER("A0321", "黑名单用户"),
    ACCOUNT_FROZEN("A0322", "账号被冻结"),
    ILLEGAL_IP_ADDRESS("A0323", "非法 IP 地址"),
    GATEWAY_ACCESS_RESTRICTED("A0324", "网关访问受限"),
    REGION_BLACKLIST("A0325", "地域黑名单"),

    SERVICE_ARREARS("A0330", "服务已欠费"),

    USER_SIGNATURE_EXCEPTION("A0340", "用户签名异常"),
    RSA_SIGNATURE_ERROR("A0341", "RSA 签名错误"),

    /** 二级宏观错误码  */
    USER_REQUEST_PARAMETER_ERROR("A0400", "用户请求参数错误"),
    CONTAINS_ILLEGAL_MALICIOUS_REDIRECT_LINK("A0401", "包含非法恶意跳转链接"),
    INVALID_USER_INPUT("A0402", "无效的用户输入"),

    REQUEST_REQUIRED_PARAMETER_IS_EMPTY("A0410", "请求必填参数为空"),

    REQUEST_PARAMETER_VALUE_EXCEEDS_ALLOWED_RANGE("A0420", "请求参数值超出允许的范围"),
    PARAMETER_FORMAT_MISMATCH("A0421", "参数格式不匹配"),

    USER_INPUT_CONTENT_ILLEGAL("A0430", "用户输入内容非法"),
    CONTAINS_PROHIBITED_SENSITIVE_WORDS("A0431", "包含违禁敏感词"),

    USER_OPERATION_EXCEPTION("A0440", "用户操作异常"),

    /** 二级宏观错误码  */
    USER_REQUEST_SERVICE_EXCEPTION("A0500", "用户请求服务异常"),
    REQUEST_LIMIT_EXCEEDED("A0501", "请求次数超出限制"),
    REQUEST_CONCURRENCY_LIMIT_EXCEEDED("A0502", "请求并发数超出限制"),
    USER_OPERATION_PLEASE_WAIT("A0503", "用户操作请等待"),
    WEBSOCKET_CONNECTION_EXCEPTION("A0504", "WebSocket 连接异常"),
    WEBSOCKET_CONNECTION_DISCONNECTED("A0505", "WebSocket 连接断开"),
    USER_DUPLICATE_REQUEST("A0506", "请求过于频繁，请稍后再试。"),

    /** 二级宏观错误码  */
    USER_RESOURCE_EXCEPTION("A0600", "用户资源异常"),
    ACCOUNT_BALANCE_INSUFFICIENT("A0601", "账户余额不足"),
    USER_DISK_SPACE_INSUFFICIENT("A0602", "用户磁盘空间不足"),
    USER_MEMORY_SPACE_INSUFFICIENT("A0603", "用户内存空间不足"),
    USER_OSS_CAPACITY_INSUFFICIENT("A0604", "用户 OSS 容量不足"),
    USER_QUOTA_EXHAUSTED("A0605", "用户配额已用光"),
    USER_RESOURCE_NOT_FOUND("A0606", "用户资源不存在"),

    /** 二级宏观错误码  */
    UPLOAD_FILE_EXCEPTION("A0700", "上传文件异常"),
    UPLOAD_FILE_TYPE_MISMATCH("A0701", "上传文件类型不匹配"),
    UPLOAD_FILE_TOO_LARGE("A0702", "上传文件太大"),
    UPLOAD_IMAGE_TOO_LARGE("A0703", "上传图片太大"),
    UPLOAD_VIDEO_TOO_LARGE("A0704", "上传视频太大"),
    UPLOAD_COMPRESSED_FILE_TOO_LARGE("A0705", "上传压缩文件太大"),

    DELETE_FILE_EXCEPTION("A0710", "删除文件异常"),

    /** 二级宏观错误码  */
    USER_CURRENT_VERSION_EXCEPTION("A0800", "用户当前版本异常"),
    USER_INSTALLED_VERSION_NOT_MATCH_SYSTEM("A0801", "用户安装版本与系统不匹配"),
    USER_INSTALLED_VERSION_TOO_LOW("A0802", "用户安装版本过低"),
    USER_INSTALLED_VERSION_TOO_HIGH("A0803", "用户安装版本过高"),
    USER_INSTALLED_VERSION_EXPIRED("A0804", "用户安装版本已过期"),
    USER_API_REQUEST_VERSION_NOT_MATCH("A0805", "用户 API 请求版本不匹配"),
    USER_API_REQUEST_VERSION_TOO_HIGH("A0806", "用户 API 请求版本过高"),
    USER_API_REQUEST_VERSION_TOO_LOW("A0807", "用户 API 请求版本过低"),

    /** 二级宏观错误码  */
    USER_PRIVACY_NOT_AUTHORIZED("A0900", "用户隐私未授权"),
    USER_PRIVACY_NOT_SIGNED("A0901", "用户隐私未签署"),
    USER_CAMERA_NOT_AUTHORIZED("A0903", "用户相机未授权"),
    USER_PHOTO_LIBRARY_NOT_AUTHORIZED("A0904", "用户图片库未授权"),
    USER_FILE_NOT_AUTHORIZED("A0905", "用户文件未授权"),
    USER_LOCATION_INFORMATION_NOT_AUTHORIZED("A0906", "用户位置信息未授权"),
    USER_CONTACTS_NOT_AUTHORIZED("A0907", "用户通讯录未授权"),

    /** 二级宏观错误码  */
    USER_DEVICE_EXCEPTION("A1000", "用户设备异常"),
    USER_CAMERA_EXCEPTION("A1001", "用户相机异常"),
    USER_MICROPHONE_EXCEPTION("A1002", "用户麦克风异常"),
    USER_EARPIECE_EXCEPTION("A1003", "用户听筒异常"),
    USER_SPEAKER_EXCEPTION("A1004", "用户扬声器异常"),
    USER_GPS_POSITIONING_EXCEPTION("A1005", "用户 GPS 定位异常"),


    PET_NOT_FOUND("P0001", "宠物不存在"),




    SYSTEM_ERROR("S0001", "系统执行出错"),;






    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    private String code;

    private String msg;

    @Override
    public String toString() {
        return "{" +
                "\"code\":\"" + code + '\"' +
                ", \"msg\":\"" + msg + '\"' +
                '}';
    }


    public static ResultCode getValue(String code) {
        for (ResultCode value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return SYSTEM_ERROR; // 默认系统执行错误
    }
}