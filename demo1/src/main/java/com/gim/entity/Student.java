package com.gim.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Gim
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_student")
@org.hibernate.annotations.Table(appliesTo = "t_student",comment = "学生")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint comment 'id'")
    private Long id;

    @Column(columnDefinition = "varchar(255) comment 'name'")
    private String name;


    @Column(columnDefinition = "tinyint(1) comment '类型'")
    private Integer type;

    @Column(columnDefinition = "tinyint(1) comment 'sex'")
    private Integer sex;

    @Column(columnDefinition = "varchar(255) comment 'class name'")
    private String className;

    @Column(columnDefinition = "tinyint(1) comment 'level'")
    private Integer level;
}
