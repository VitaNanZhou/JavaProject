package com.example.demo.entity.msg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value="错误码结果集")
public class ErrorMsg {

    @ApiModelProperty(value="错误码键")
    private String key;

    @ApiModelProperty(value="英文描述")
    private String enDescription;

    @ApiModelProperty(value="中文描述")
    private String zhDescription;
}
