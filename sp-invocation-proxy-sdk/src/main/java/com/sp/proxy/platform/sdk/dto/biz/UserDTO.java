package com.sp.proxy.platform.sdk.dto.biz;

import com.sp.proxy.platform.sdk.annotation.Column;
import com.sp.proxy.platform.sdk.dto.OptionDTO;
import lombok.Data;

@Data
public class UserDTO {

    @Column(label = "用户名",order = 1)
    private String userName;

    @Column(label = "密码",order = 2)
    private String password;

    private Integer status;

    @Column(label = "ops",order = 0)
    private OptionDTO optionDTO;

}
