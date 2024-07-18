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
 * @description: 医疗咨询实体
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("medical_news")
public class MedicalNews {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 医疗咨询名字
     */
    private String newsName;

    /**
     * 医疗咨询关键字
     */
    private String newsKey;

    /**
     * 咨询的详细内容
     */
    private String newsContent;

    /**
     * 包含的图片地址
     */
    private String imgPath;

    /**
     * 关联的疾病id
     */
    private Integer relationIllness;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}