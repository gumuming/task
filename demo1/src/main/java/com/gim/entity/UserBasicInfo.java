package com.gim.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * 用户基本信息,主要用来展示
 * eg: 这里是用户在数据库中的部分字段信息
 * @author tes_hw
 */
@ApiModel(value = "com.kalacheng.buscommon.model.UserBasicInfo", description = "用户基本信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicInfo extends UserStatus implements Serializable {
    private static final long serialVersionUID = 1L;



    @ApiModelProperty(value = "用户ID", name = "userId")
    public Long userId;

    @ApiModelProperty(value = "用户ID", name = "uid")
    public Long uid;

    @ApiModelProperty(value = "昵称", name = "username")
    public String username;

    @ApiModelProperty(value = "手机号", name = "mobile")
    public String mobile;

    @ApiModelProperty(value = "头像", name = "avatar")
    public String avatar;

    @ApiModelProperty(value = "头像缩略图", name = "avatarThumb")
    public String avatarThumb;

    @ApiModelProperty(value = "性别 1:男 2:女", name = "sex")
    public Integer sex;

    @ApiModelProperty(value = "封面图", name = "thumb")
    public String thumb;

    @ApiModelProperty(value = "生日", name = "birthday")
    public String birthday;

    @ApiModelProperty(value = "年龄")
    public Integer age;

    @ApiModelProperty(value = "直播封面", name = "liveThumb")
    public String liveThumb;

    @ApiModelProperty(value = "角色 0:普通用户 1:主播", name = "role")
    public Integer role;

    @ApiModelProperty(value = "身高", name = "height")
    public Integer height;

    @ApiModelProperty(value = "体重", name = "weight")
    public Double weight;

    @ApiModelProperty(value = "职业", name = "vocation")
    public String vocation;

    @ApiModelProperty(value = "星座", name = "constellation")
    public String constellation;

    @ApiModelProperty(value = "地址", name = "address")
    public String address;

    @ApiModelProperty(value = "个性签名", name = "signature")
    public String signature;

    @ApiModelProperty(value = "微信号", name = "wechatNo")
    public String wechatNo;

    @ApiModelProperty(value = "省份", name = "province")
    public String province;

    @ApiModelProperty(value = "城市", name = "city")
    public String city;

    @ApiModelProperty(value = "经度", name = "lng")
    public Double lng;

    @ApiModelProperty(value = "纬度", name = "lat")
    public Double lat;

    @ApiModelProperty(value = "群id", name = "groupId")
    public Long groupId;

    @ApiModelProperty(value = "金币/充值金额 (男朋友中为钻石)", name = "coin")
    public Double coin;

    @ApiModelProperty(value = "映票余额/可提现金额", name = "votes")
    public Double votes;

    @ApiModelProperty(value = "靓号", name = "goodnum")
    public String goodnum;

    @ApiModelProperty(value = "pid", name = "pid")
    public Long pid;

    @ApiModelProperty(value = "用户等级", name = "userGrade")
    public Integer userGrade;

    @ApiModelProperty(value = "主播等级")
    public Integer anchorGrade;

    @ApiModelProperty(value = "财富等级", name = "wealthGrade")
    public Integer wealthGrade;

    @ApiModelProperty(value = "贵族等级", name = "nobleGrade")
    public Integer nobleGrade;

    @ApiModelProperty(value = "个人资料图片,逗号隔开", name = "portrait")
    public String portrait;

    @ApiModelProperty(value = "视频通话费用", name = "videoCoin")
    public Double videoCoin;

    @ApiModelProperty(value = "语音通话费用", name = "voiceCoin")
    public Double voiceCoin;

    @ApiModelProperty(value = "注册时间", name = "createTime")
    public String createTime;

    @ApiModelProperty(value = "海报", name = "poster")
    public String poster;

    @ApiModelProperty(value = "剩余的注册赠送通话次数", name = "registerCallSecond")
    public Integer registerCallSecond;

    @ApiModelProperty(value = "注册赠送通话时间(单位为分钟)", name = "registerCallTime")
    public Integer registerCallTime;

    @ApiModelProperty(value = "短视频剩余可观看私密视频次数", name = "readShortVideoNumber")
    public Integer readShortVideoNumber;

//    public UserBasicInfo(Integer isSvip, Integer iszombiep, Integer iszombie, Integer issuper, Integer whetherEnablePositioningShow,
//                         Integer isYouthModel, Integer isLocation, Integer isPush, Integer isTone, Integer chargeShow, Integer devoteShow, Integer joinRoomShow,
//                         Integer broadCast, Integer isNotDisturb, Integer vipType, Integer status, Integer isAnchorAuth, Integer onlineStatus,
//                         Integer userSetOnlineStatus, Integer liveStatus, Integer oooLiveStatus, Integer voiceStatus, Integer liveType,
//                         Long roomId, String showId, Long otherUid, Integer canLinkMic, Double roomVotes, Double roomPKVotes, Long linkOtherSid, Integer linkOtherStatus,
//                         Long roomPkSid, Integer roomType, Integer roomIsVideo, Integer hostVolumed, Integer noTalking) {
//        super(isSvip, iszombiep, iszombie, issuper, whetherEnablePositioningShow, isYouthModel, isLocation, isPush, isTone, chargeShow, devoteShow, joinRoomShow, broadCast, isNotDisturb, vipType, status, isAnchorAuth, onlineStatus, userSetOnlineStatus, liveStatus, oooLiveStatus, voiceStatus, liveType, roomId, showId, otherUid, canLinkMic, roomVotes, roomPKVotes, linkOtherSid, linkOtherStatus, roomPkSid, roomType, roomIsVideo, hostVolumed, noTalking);
//    }
}
