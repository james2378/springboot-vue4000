package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *项目类型：(ProjectType)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ProjectType")
public class ProjectType implements Serializable {

    //ProjectType编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_type_id")
    private Integer project_type_id;
   // 项目类型
   @Basic
    private String project_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
