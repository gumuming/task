package com.gim.repo;

import com.gim.entity.ApiUserInfo;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
                        " FROM (SELECT  userid AS `userId`, userid AS `uid`, username, mobile, avatar, avatar_thumb, sex, live_thumb , birthday, " +
                        " register_call_second, register_call_time, role, province, city, lng, lat, group_id, coin, votes, goodnum, pid, user_grade, anchor_grade," +
                        " wealth_grade, noble_grade, portrait, height, weight, vocation, constellation, address, signature, wechat AS `wechat_no`, video_coin," +
                        " voice_coin, create_time, is_svip, iszombiep, issuper, whether_enable_positioning_show, is_youth_model, is_location, is_push, is_tone," +
                        " charge_show, devote_show, join_room_show, broad_cast, is_not_disturb, is_svip AS `vip_type`, user_set_online_status, status, iszombie, " +
                        " online_status, live_status, voice_status, ooo_live_status, is_anchor_auth, TIMESTAMPDIFF(YEAR, birthday, CURDATE()) age," +
                        " read_short_video_number " +
                        " FROM app_user " +
                        " WHERE userid = " + k_userId + ") AS au " +
                        " LEFT JOIN app_users_vip auv ON auv.userid = au.userid AND DATE_FORMAT(now(), '%Y-%m-%d %H:%i:%s') <= DATE_FORMAT(auv.endtime, '%Y-%m-%" +
                        " %H:%i:%s') " +
                        " LEFT JOIN app_grade ug ON ug.grade = au.user_grade AND ug.type = 1 " +
                        " LEFT JOIN app_grade wg ON wg.grade = au.wealth_grade AND wg.type = 2 " +
                        " LEFT JOIN app_grade ag ON ag.grade = au.anchor_grade AND ag.type = 3 " +
                        " LEFT JOIN app_grade ng ON ng.grade = au.noble_grade AND ng.type = 4 " +
                        " LEFT JOIN cfg_pay_call_one_vs_one cpcovo ON cpcovo.user_id = au.userid ";

        final Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(ApiUserInfo.class));
        return (ApiUserInfo) nativeQuery.getSingleResult();

    }
}
