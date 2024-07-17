package qihang.smart.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @description: 药品实体类
 * @author: zhqihang
 * @date: 2024/07/17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("medicine")
public class Medicine {

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 药物名字
     */
    private String medicineName;

    /**
     * 关键字搜索
     */
    private String keyword;

    /**
     * 药物的功效
     */
    private String medicineEffect;

    /**
     * 药物的品牌
     */
    private String medicineBrand;

    /**
     * 药物的相互作用
     */
    private String interaction;

    /**
     * 禁忌
     */
    private String taboo;

    /**
     * 用法用量
     */
    private String usAge;

    /**
     * 药物的类型，0代表西药，1中药，2中成药
     */
    private Integer medicineType;

    /**
     * 药物的图片地址
     */
    private String imgPath;

    /**
     * 药物的价格
     */
    private BigDecimal medicinePrice;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}