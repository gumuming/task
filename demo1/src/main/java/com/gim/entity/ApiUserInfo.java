package com.gim.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户的基本信息,包括一些额外的东西,如果说贵族对应的图片,
 * eg:这里面都是非数据库字段的扩展字段
 */
@Data
@JsonIgnoreProperties
@ApiModel(value = "com.kalacheng.buscommon.model.ApiUserInfo", description = "APP登录接口返回值")
public class ApiUserInfo extends UserBasicInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "贵族等级图片")
    public String nobleGradeImg;

    @ApiModelProperty(value = "贵族名称")
    public String nobleName;

    @ApiModelProperty(value = "用户等级图片")
    public String userGradeImg;

    @ApiModelProperty(value = "主播等级图片")
    public String anchorGradeImg;

    @ApiModelProperty(value = "财富等级图片")
    public String wealthGradeImg;

    @Transient
    @ApiModelProperty(value = "用户勋章logo")
    public String userLogo;

    @Transient
    @ApiModelProperty(value = "是否能开播 1:否 0:是")
    public int isLive;



    @Transient
    @ApiModelProperty(value = "用户token")
    public String user_token;

    @Transient
    @ApiModelProperty(value = "是否第一次登录 1:是 2:不是")
    public int isFirstLogin;

    @Transient
    @ApiModelProperty(value = "是否弹出邀请码 1:弹出 2:不弹出")
    public int isPid;

    @Transient
    @ApiModelProperty(value = "总消费金币(展示) (男朋友中为钻石)")
    public String totalConsumeCoinStr;

    @Transient
    @ApiModelProperty(value = "总收益映票(展示)")
    public String totalIncomeVotesStr;

    @Transient
    @ApiModelProperty(value = "谁看过我人数")
    public int readMeUsersNumber;



    @Transient
    @ApiModelProperty(value = "我关注的数量")
    public int followNum;

    @Transient
    @ApiModelProperty(value = "关注我的数量（粉丝数量）")
    public int fansNum;

    @Transient
    @ApiModelProperty(value = "点赞数")
    public int likeNum;

    @Transient
    @ApiModelProperty(value = "关注状态 0:未关注， 1：已关注")
    public int followStatus;



    @Transient
    @ApiModelProperty(value = "打赏人数")
    public int rewardNum;


    @Transient
    @ApiModelProperty(value = "谁看过我是否需要贵族开关 0:需要 1:不需要", name = "isVipSee")
    public int isVipSee;

    @Transient
    @ApiModelProperty(value = "前端显示的状态字段：0:离线 1:忙碌 2:在线 3:通话中 4:看直播 5:匹配中 6:直播中 7:离开", name = "showStatus")
    public int showStatus;




    @Transient
    @ApiModelProperty(value = "商城内科学计数转换")
    public String coinShow;


    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "财富勋章logo")
    public String wealthLogo;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "贵族勋章logo")
    public String nobleLogo;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "直播间礼物金额")
    public double liveCoin;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "总消费金币 (男朋友中为钻石)")
    public double totalConsumeCoin;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "总收益映票")
    public double totalIncomeVotes;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "贵族剩余天数")
    public long nobleExpireDay;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "体重Str")
    public String weightStr;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "查看微信号所需金币 (男朋友中为钻石)")
    public double wechatCoin;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "查看手机号所需金币 (男朋友中为钻石)")
    public double mobileCoin;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "主播审核状态")
    public int anchorAuditStatus;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "主播审核原因")
    public String anchorAuditReason;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "当前用户跟直播间的关系 1:当前直播间主播 2:管理员 3:普通用户", name = "relation")
    public int relation;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "对方用户跟直播间的关系 1:当前直播间主播 2:管理员 3:普通用户", name = "toRelation")
    public int toRelation;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "是否禁言 1:已禁言 0:未禁言", name = "isShutUp")
    public int isShutUp;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "是否填写邀请码 1:不弹出 0:弹出", name = "isAlertCode")
    public int isAlertCode;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "用户对当前直播间总贡献值")
    public double currContValue;

}
