package qihang.smart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @description: 疾病实体类
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("illness")
public class Illness {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 疾病所属种类id
     */
    private Integer kindId;

    /**
     * 疾病的名字
     */
    private String illnessName;

    /**
     * 引起的原因
     */
    private String includeReason;

    /**
     * 主要的症状
     */
    private String illnessSymptom;

    /**
     * 特殊的症状
     */
    private String specialSymptom;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private IllnessKind kind;

    @TableField(exist = false)
    private IllnessMedicine illnessMedicine;

}
