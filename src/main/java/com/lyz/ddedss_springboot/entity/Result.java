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
@TableName("result")
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    @TableField("student_id")
    private Integer studentId;

    /**
     * 考试id
     */
    @TableField("exam_id")
    private Integer examId;

    /**
     * 科目id
     */
    @TableField("subject_id")
    private Integer subjectId;

    /**
     * 分数（-1则为还未打分状态）
     */
    @TableField("score")
    private Integer score;

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
