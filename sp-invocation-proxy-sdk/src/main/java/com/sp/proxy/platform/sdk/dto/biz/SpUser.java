package com.sp.proxy.platform.sdk.dto.biz;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sp.proxy.platform.sdk.annotation.Column;
import lombok.Data;

import java.util.Date;

@Data
public class SpUser {

    @Column(label = "用户ID", order = 0)
    private Long id;

    @Column(label = "工号", order = 1)
    private String userCode;

    @Column(label = "手机号", order = 3)
    private String phoneNum;

    @Column(label = "自定义登录名", order = 4)
    private String customLoginName;

    @Column(label = "邮箱", order = 5)
    private String email;

    @Column(label = "身份证号", order = 6)
    private String idNumber;

    @Column(label = "真实姓名", order = 7)
    private String realName;

    @Column(label = "采购中台用户id", order = 8)
    private Long userInfoId;

    @Column(label = "密码", order = 9)
    private String password;

    @Column(label = "OA密码", order = 10)
    private String oaPassword;

    @Column(label = "支付密码", order = 11)
    private String payPassword;

    @Column(label = "辉创userId", order = 12)
    private Long otherUserId;

    @Column(label = "姓别", order = 13)
    private String gender;

    @Column(label = "生日", order = 14)
    private Date birthDate;

    @Column(label = "用户类型", order = 15)
    private String userType;

    @Column(label = "是否员工", order = 16)
    private Boolean isEmployee;

    @Column(label = "是否是同步的数据", order = 17)
    private Boolean isSyncData;

    @Column(label = "是否禁用", order = 18)
    private Boolean isDisable;

    @Column(label = "组织全路径名", order = 19)
    private String fullPathName;

    @Column(label = "组织全路径code", order = 20)
    private String fullPathCode;

    @Column(label = "组织编码", order = 21)
    private String organizationCode;

    @Column(label = "组织名", order = 22)
    private String organizationName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(label = "有效期", order = 23)
    private Date endTime;

    @Column(label = "备注", order = 24)
    private String remark;

    @Column(label = "角色", order = 25)
    private String roleCode;
    @Column(label = "角色名", order = 26)
    private String roleName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(label = "创建时间", order = 27)
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(label = "更新时间", order = 28)
    private Date updateTime;

    @Column(label = "创建人", order = 29)
    private String createBy;

    @Column(label = "更新人", order = 30)
    private String updateBy;

}
