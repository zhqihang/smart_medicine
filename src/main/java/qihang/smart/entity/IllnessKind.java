package qihang.smart.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
/**
 * @description: 疾病类型实体类
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("illness_kind")
public class IllnessKind {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 疾病种类名字
     */
    private String name;

    /**
     * 疾病描述
     */
    private String info;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
