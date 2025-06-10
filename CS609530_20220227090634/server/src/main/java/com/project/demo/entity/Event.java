package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *比赛项目：(Event)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "Event")
public class Event implements Serializable {

    //Event编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer event_id;
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
   // 比赛封面
   @Basic
    private String competition_cover;
   // 比赛简介
   @Basic
    private String competition_introduction;
    // 点击数
    @Basic
    private Integer hits;
	// 计时器标题
	@Basic
	private String timer_title;
	
	// 计时开始时间
	@Basic
	private Timestamp timing_start_time;
	
	// 计时结束时间
	@Basic
	private Timestamp timing_end_time;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
