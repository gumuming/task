package com.gim.live;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Gim
 */
@Component
public class ApiUserDao {

    @Resource
    private EntityManager entityManager;



    //我守护的
    public List<GuardUserDto> getGuardUserDtoPageLists(Integer pageIndex, Integer pageSize, long targetUserId) {
        String sql = "SELECT auu.username,auu.userid as 'user_id', auu.avatar as 'user_head_img', aut.userid as 'anchor_id', aut.avatar as 'anchor_id_img', " +
                "agu.addtime as 'start_time', agu.endtime as 'end_time', now() as 'now_time', agu.free_day, 0 as 'guard_day', 0 as 'left_day', agu.addtime FROM app_guard_users agu LEFT JOIN app_user auu ON agu.uid = auu.userid " +
                " LEFT JOIN app_user aut ON agu.anchor_id = aut.userid where agu.uid = %s and aut.userid is not null limit %s,%s";

        final Query nativeQuery = entityManager.createNativeQuery(String.format(sql, targetUserId, pageIndex, pageSize));
        nativeQuery.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(GuardUserDto.class));
        return nativeQuery.getResultList();
    }

}
