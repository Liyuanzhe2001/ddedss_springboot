package com.lyz.ddedss_springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 自增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 字段
     */
    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("email")
    private String email;

    /**
     * 不是数据库字段
     */
    @TableField(exist = false)
    private String xx;

}
