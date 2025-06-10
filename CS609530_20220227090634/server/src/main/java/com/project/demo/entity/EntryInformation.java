package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *参赛信息：(EntryInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "EntryInformation")
public class EntryInformation implements Serializable {

    //EntryInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_information_id")
    private Integer entry_information_id;
   // 项目名称
   @Basic
    private String entry_name;
   // 项目类型
   @Basic
    private String project_type;
   // 比赛地点
   @Basic
    private String competition_place;
   // 比赛时间
   @Basic
    private Timestamp game_time;
   // 学生编号
   @Basic
    private Integer student_number;
   // 学生姓名
   @Basic
    private String student_name;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
