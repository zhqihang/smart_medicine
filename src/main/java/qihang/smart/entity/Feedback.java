package qihang.smart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description: 反馈实体类
 * @author: zhqihang
 * @date: 2024/07/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("feedback")
public class Feedback {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 反馈用户
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 反馈标题
     */
    private String title;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}