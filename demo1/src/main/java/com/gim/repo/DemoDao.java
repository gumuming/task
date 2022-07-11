package com.gim.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gim.entity.ApiUserInfo;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Gim
 */
@Component
public class DemoDao {

    @Resource
    private EntityManager entityManager;

    public ApiUserInfo getUserInfo(long k_userId) {
        String sql =
                "SELECT ag.grade_icon AS anchorGradeImg , ug.grade_icon AS userGradeImg , ng.grade_icon AS  nobleGradeImg, wg.grade_icon AS  wealthGradeImg, " +
                        " ng.`name` AS nobleName, cpcovo.poster AS thumb, " +
                        " au.* " +
                        " FROM (SELECT  userid AS `userId`, userid AS `uid`, username, mobile, avatar, avatar_thumb as avatarThumb, sex, live_thumb as liveThumb , birthday, " +
                        " register_call_second registerCallSecond, register_call_time registerCallTime, role, province, city, lng, lat, group_id groupId, coin, votes, goodnum, pid, user_grade userGrade, anchor_grade anchorGrade," +
                        " wealth_grade wealthGrade, noble_grade nobleGrade, portrait, height, weight, vocation, constellation, address, signature, wechat AS `wechatNo`, video_coin videoCoin," +
                        " voice_coin voiceCoin, create_time createTime, is_svip isSvip, iszombiep, issuper, whether_enable_positioning_show whetherEnablePositioningShow, is_youth_model isYouthModel, is_location isLocation, is_push isPush, is_tone isTone," +
                        " charge_show chargeShow, devote_show devoteShow, join_room_show joinRoomShow, broad_cast broadCast, is_not_disturb isNotDisturb, is_svip AS `vipType`, user_set_online_status userSetOnlineStatus, status, iszombie, " +
                        " online_status onlineStatus, live_status liveStatus, voice_status voiceStatus, ooo_live_status oooLiveStatus, is_anchor_auth isAnchorAuth, TIMESTAMPDIFF(YEAR, birthday, CURDATE()) age," +
                        " read_short_video_number readShortVideoNumber " +
                        " FROM app_user " +
                        " WHERE userid = " + k_userId + ") AS au " +
                        " LEFT JOIN app_users_vip auv ON auv.userid = au.userid AND DATE_FORMAT(now(), '%Y-%m-%d %H:%i:%s') <= DATE_FORMAT(auv.endtime, '%Y-%m-%" +
                        " %H:%i:%s') " +
                        " LEFT JOIN app_grade ug ON ug.grade = au.userGrade AND ug.type = 1 " +
                        " LEFT JOIN app_grade wg ON wg.grade = au.wealthGrade AND wg.type = 2 " +
                        " LEFT JOIN app_grade ag ON ag.grade = au.anchorGrade AND ag.type = 3 " +
                        " LEFT JOIN app_grade ng ON ng.grade = au.nobleGrade AND ng.type = 4 " +
                        " LEFT JOIN cfg_pay_call_one_vs_one cpcovo ON cpcovo.user_id = au.userid ";

        final Query nativeQuery = entityManager.createNativeQuery(sql);
               nativeQuery
                .unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> resultList = nativeQuery.getResultList();
        List<ApiUserInfo> list = resultList.stream().map(a -> mapConvertToObject(a, ApiUserInfo.class)).collect(Collectors.toList());
        return list.get(0);
    }

    /**
     * 将map转换为POJO对象
     * @param map map
     * @param beanClass class
     * @return r
     */
    public static <T> T mapConvertToObject(Map<String, Object> map, Class<T> beanClass) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(map, beanClass);
    }
}
