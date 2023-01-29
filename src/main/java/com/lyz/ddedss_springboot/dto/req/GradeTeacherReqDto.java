package com.lyz.ddedss_springboot.dto.req;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lyz.ddedss_springboot.vo.GradeTeacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GradeTeacherReqDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<GradeTeacher> gradeTeachers;

}
