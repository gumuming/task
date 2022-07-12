package com.gim.live;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "com.kalacheng.buscommon.model.GuardUserDto", description = "守护结果")
public class GuardUserDto {


    @ApiModelProperty(value = "用户ID")
    public long userId;
    @ApiModelProperty(value = "用户头像")
    public String userHeadImg;
    @ApiModelProperty(value = "用户名称")
    public String  username;
    @ApiModelProperty(value = "被守护用户ID")
    public long anchorId;
    @ApiModelProperty(value = "被守护用户头像")
    public String anchorIdImg;
    @ApiModelProperty(value = "开始守护时间")
    public Date startTime;
    @ApiModelProperty(value = "结束守护时间")
    public Date endTime;
    @ApiModelProperty(value = "当前时间")
    public Date nowTime;
    @ApiModelProperty(value = "创建时间")
    public Date addtime;
    @ApiModelProperty(value = "欠费是天数")
    public long freeDay;
    @ApiModelProperty(value = "已守护天数")
    public long guardDay;
    @ApiModelProperty(value = "剩余天数")
    public long leftDay;

}
