package com;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 * @author tes_hw
 */
@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T,Long>, JpaSpecificationExecutor<T> {


//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    default <S extends T> S save(S t) {
//        final JpaEntityInformation entityInformation = SpringUtil.getBean(JpaEntityInformation.class);
//        Long id = (Long) entityInformation.getId(t);
//
//        if (id == null || id == 0){
//            return save(t);
//        }
//
//        T old = findById(id).orElse(null);
//        if (old == null){
//            throw new ServiceException("没有找到对应对象");
//        }
//        Field[] fields = t.getClass().getDeclaredFields();
//        try {
//            for (Field field : fields) {
//                //去除静态属性
//                if (Modifier.isStatic(field.getModifiers())){
//                    continue;
//                }
//
//                field.setAccessible(true);
//                Object value = field.get(t);
//                if (value == null){
//                    continue;
//                }
//                field.set(old, value);
//            }
//        } catch (IllegalAccessException e) {
//            throw new ServiceException("没有找到对应对象");
//        }
//        return (S) saveAndFlush(old);
//    }



//
//    @Override
//    default <S extends T> S save(S entity) {
//        EntityManager em = SpringUtil.getBean(EntityManager.class);
//        JpaEntityInformation<T, ?> entityInformation = SpringUtil.getBean(JpaEntityInformation.class);
//        //获取ID
//         Long entityId = (Long) entityInformation.getId(entity);
//        T managedEntity;
//        T mergedEntity;
//        if(entityId == null){
//            em.persist(entity);
//            mergedEntity = entity;
//        }else{
//            managedEntity = this.findById(entityId).get();
//            if (managedEntity == null) {
//                em.persist(entity);
//                mergedEntity = entity;
//            } else {
//                BeanUtils.copyProperties(entity, managedEntity, getNullProperties(entity));
//                em.merge(managedEntity);
//                mergedEntity = managedEntity;
//            }
//        }
//        return entity;
//    }
//
//
//
//
//    /**
//     * 获取对象的空属性
//     */
//     static String[] getNullProperties(Object src) {
//        //1.获取Bean
//        BeanWrapper srcBean = new BeanWrapperImpl(src);
//        //2.获取Bean的属性描述
//        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
//        //3.获取Bean的空属性
//        Set<String> properties = new HashSet<>();
//        for (PropertyDescriptor propertyDescriptor : pds) {
//            String propertyName = propertyDescriptor.getName();
//            Object propertyValue = srcBean.getPropertyValue(propertyName);
//           if (StringUtils.isEmpty(propertyValue)) {
//                srcBean.setPropertyValue(propertyName, null);
//                properties.add(propertyName);
//            }
//        }
//        return properties.toArray(new String[0]);
//    }

}
