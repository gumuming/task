package com.gim.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * 用户基本信息,主要用来展示
 * eg: 这里是用户在数据库中的部分字段信息
 * @author tes_hw
 */
@ApiModel(value = "com.kalacheng.buscommon.model.UserBasicInfo", description = "用户基本信息")
@NoArgsConstructor
public class UserBasicInfo extends UserStatus implements Serializable {
    private static final long serialVersionUID = 1L;



    @ApiModelProperty(value = "用户ID", name = "userId")
    public long userId;

    @ApiModelProperty(value = "用户ID", name = "uid")
    public long uid;

    @ApiModelProperty(value = "昵称", name = "username")
    public String username;

    @ApiModelProperty(value = "手机号", name = "mobile")
    public String mobile;

    @ApiModelProperty(value = "头像", name = "avatar")
    public String avatar;

    @ApiModelProperty(value = "头像缩略图", name = "avatarThumb")
    public String avatarThumb;

    @ApiModelProperty(value = "性别 1:男 2:女", name = "sex")
    public int sex;

    @ApiModelProperty(value = "封面图", name = "thumb")
    public String thumb;

    @ApiModelProperty(value = "生日", name = "birthday")
    public String birthday;

    @ApiModelProperty(value = "年龄")
    public int age;

    @ApiModelProperty(value = "直播封面", name = "liveThumb")
    public String liveThumb;

    @ApiModelProperty(value = "角色 0:普通用户 1:主播", name = "role")
    public int role;

    @ApiModelProperty(value = "身高", name = "height")
    public int height;

    @ApiModelProperty(value = "体重", name = "weight")
    public double weight;

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
    public double lng;

    @ApiModelProperty(value = "纬度", name = "lat")
    public double lat;

    @ApiModelProperty(value = "群id", name = "groupId")
    public long groupId;

    @ApiModelProperty(value = "金币/充值金额 (男朋友中为钻石)", name = "coin")
    public double coin;

    @ApiModelProperty(value = "映票余额/可提现金额", name = "votes")
    public double votes;

    @ApiModelProperty(value = "靓号", name = "goodnum")
    public String goodnum;

    @ApiModelProperty(value = "pid", name = "pid")
    public long pid;

    @ApiModelProperty(value = "用户等级", name = "userGrade")
    public int userGrade;

    @ApiModelProperty(value = "主播等级")
    public int anchorGrade;

    @ApiModelProperty(value = "财富等级", name = "wealthGrade")
    public int wealthGrade;

    @ApiModelProperty(value = "贵族等级", name = "nobleGrade")
    public int nobleGrade;

    @ApiModelProperty(value = "个人资料图片,逗号隔开", name = "portrait")
    public String portrait;

    @ApiModelProperty(value = "视频通话费用", name = "videoCoin")
    public double videoCoin;

    @ApiModelProperty(value = "语音通话费用", name = "voiceCoin")
    public double voiceCoin;

    @ApiModelProperty(value = "注册时间", name = "createTime")
    public String createTime;

    @ApiModelProperty(value = "海报", name = "poster")
    public String poster;

    @ApiModelProperty(value = "剩余的注册赠送通话次数", name = "registerCallSecond")
    public int registerCallSecond;

    @ApiModelProperty(value = "注册赠送通话时间(单位为分钟)", name = "registerCallTime")
    public int registerCallTime;

    @ApiModelProperty(value = "短视频剩余可观看私密视频次数", name = "readShortVideoNumber")
    public int readShortVideoNumber;

    public UserBasicInfo(int isSvip, int iszombiep, int iszombie, int issuper, int whetherEnablePositioningShow, int isYouthModel, int isLocation, int isPush, int isTone, int chargeShow, int devoteShow, int joinRoomShow, int broadCast, int isNotDisturb, int vipType, int status, int isAnchorAuth, int onlineStatus, int userSetOnlineStatus, int liveStatus, int oooLiveStatus, int voiceStatus, int liveType, long roomId, String showId, long otherUid, int canLinkMic, double roomVotes, double roomPKVotes, long linkOtherSid, int linkOtherStatus, long roomPkSid, int roomType, int roomIsVideo, int hostVolumed, int noTalking) {
        super(isSvip, iszombiep, iszombie, issuper, whetherEnablePositioningShow, isYouthModel, isLocation, isPush, isTone, chargeShow, devoteShow, joinRoomShow, broadCast, isNotDisturb, vipType, status, isAnchorAuth, onlineStatus, userSetOnlineStatus, liveStatus, oooLiveStatus, voiceStatus, liveType, roomId, showId, otherUid, canLinkMic, roomVotes, roomPKVotes, linkOtherSid, linkOtherStatus, roomPkSid, roomType, roomIsVideo, hostVolumed, noTalking);
    }
}
