package com.lyz.ddedss_springboot.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("evaluate")
public class Evaluate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 教师课程id
     */
    @TableField("teacher_subject_id")
    private Integer teacherSubjectId;

    /**
     * 结束（0差，1优）
     */
    @TableField("final")
    private Short final_;

    /**
     * 第几次评价
     */
    @TableField("time")
    private Integer time;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill= FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否被删除
     */
    @TableLogic("is_deleted")
    private Short isDeleted;

}
