package qihang.smart.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 浏览实体
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@TableName("pageview")
public class Pageview implements Serializable {

    /**
     * 浏览量主键id
     */
    private Integer id;

    /**
     * 浏览量
     */
    private Integer pageviews;


    /**
     * 病的id
     */
    private Integer illnessId;
}