package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *成绩排名：(ScoreRanking)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ScoreRanking")
public class ScoreRanking implements Serializable {

    //ScoreRanking编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_ranking_id")
    private Integer score_ranking_id;
   // 项目名称
   @Basic
    private String entry_name;
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
   // 比赛成绩
   @Basic
    private String score;
   // 成绩排名
   @Basic
    private String score_ranking;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
