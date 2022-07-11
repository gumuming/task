package com.gim.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 用户状态相关,包括一些其他位置的记录,
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "是否开通SVIP服务 1:是 0:否", name = "isSvip")
    public Integer isSvip;

    @ApiModelProperty(value = "是否僵尸粉 1:否 0:是", name = "iszombiep")
    public Integer iszombiep;

    @ApiModelProperty(value = "是否开启僵尸粉 1:未开启(默认) 0:已开启")
    public Integer iszombie;

    @ApiModelProperty(value = "是否超管 1:否 0:是", name = "issuper")
    public Integer issuper;

    @ApiModelProperty(value = "是否开启定位显示 0:开启 1:未开启", name = "whetherEnablePositioningShow")
    public Integer whetherEnablePositioningShow;

    @ApiModelProperty(value = "是否开启青少年模式 1:开启 2:未开启", name = "isYouthModel")
    public Integer isYouthModel;

    @ApiModelProperty(value = "是否开启定位 0:开启 1:关闭", name = "isLocation")
    public Integer isLocation;

    @ApiModelProperty(value = "是否开启消息推送 0:开启 1:关闭", name = "isPush")
    public Integer isPush;

    @ApiModelProperty(value = "是否关闭提示音 0:开启 1:关闭", name = "isTone")
    public Integer isTone;

    @ApiModelProperty(value = "充值隐身 0:不隐身 1:隐身", name = "chargeShow")
    public Integer chargeShow;

    @ApiModelProperty(value = "贡献榜排行隐身 0:不隐身 1:隐身", name = "devoteShow")
    public Integer devoteShow;

    @ApiModelProperty(value = "加入房间隐身 0:不隐身 1:隐身", name = "joinRoomShow")
    public Integer joinRoomShow;

    @ApiModelProperty(value = "全站广播功能 0:关闭功能 1:开启功能", name = "broadCast")
    public Integer broadCast;

    @ApiModelProperty(value = "是否开启勿扰 0:未开启 1:开启", name = "isNotDisturb")
    public Integer isNotDisturb;

    @ApiModelProperty(value = "是否为VIP 0:非vip 1:是vip", name = "vipType")
    public Integer vipType;

    @ApiModelProperty(value = "用户状态 1:禁用 0:解禁", name = "status")
    public Integer status;

    @ApiModelProperty(value = "主播是否认证 0:未认证 1:已认证  后台添加主播时,如果是认证状态, 需要添加认证记录", name = "isAnchorAuth")
    public Integer isAnchorAuth;

    @ApiModelProperty(value = "用户真实在线状态 0:离线 1:在线", name = "onlineStatus")
    public Integer onlineStatus;

    @ApiModelProperty(value = "用户设置的在线状态 0:在线 1:忙碌 2:离开 3:通话中", name = "userSetOnlineStatus")
    public Integer userSetOnlineStatus;

    @ApiModelProperty(value = "直播状态 0:未进行直播 1:直播主播 2:直播观众", name = "liveStatus")
    public Integer liveStatus;

    @ApiModelProperty(value = "一对一直播状态 0:未进行直播 1:通话中 2:邀请他人通话 3:正在被邀请")
    public Integer oooLiveStatus;

    @ApiModelProperty(value = "多人语音直播状态 0:不在语音房间中 2:上麦标识 3:被邀上麦中 4:被踢下麦 5:下麦标识 6:申请上麦中 8:被踢出房间", name = "voiceStatus")
    public Integer voiceStatus;

    // ---↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 上面是,在数据库的状态字段---下面是非数据库状态字段 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓---

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "直播类型 0:没有直播,无类型 1:视频直播  2:语音直播", name = "liveType")
    public Integer liveType = 0;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "直播房间号", name = "roomId")
    public Long roomId;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "直播房间标识", name = "showId")
    public String showId;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "对方Uid 包括：连麦/互动/PK", name = "otherUid")
    public Long otherUid;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "是否允许连麦 0:关 1:开", name = "canLinkMic")
    public Integer canLinkMic;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "直播间魅力值", name = "roomVotes")
    public Double roomVotes;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "直播间 PK过程中 的魅力值", name = "roomPKVotes")
    public Double roomPKVotes;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "与他人链接的会话ID（互动会话ID）", name = "linkOtherSid")
    public Long linkOtherSid;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "与他人链接的状态(互动状态) 0:未链接 1:邀请他人连麦 2:被邀请连麦 3:连麦进行中 11:邀请他人互动 12:被邀请互动 13:互动进行中 21:邀请他人PK 22:被邀请PK 23:PK中", name = "linkOtherStatus")
    public Integer linkOtherStatus;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "PK过程的ID", name = "roomPkSid")
    public Long roomPkSid;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "房间类型 0:普通房间 1:密码房间 2:门票房间 3:计时房间 4:贵族房间", name = "roomType")
    public Integer roomType;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "0:直播 1:是演示视频的直播", name = "roomIsVideo")
    public Integer roomIsVideo;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "麦克风状态 0: 关闭 1:开启", name = "hostVolumed")
    public Integer hostVolumed;

    @Transient
//    @CacheFieldNotDBField
    @ApiModelProperty(value = "被禁麦(麦克风) 0: 可以发言(默认) 1:被禁止发言", name = "noTalking")
    public Integer noTalking;


}
