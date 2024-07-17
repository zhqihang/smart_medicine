package qihang.smart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import qihang.smart.entity.Pageview;

/**
 * @description: 分页浏览实体 Mapper 接口
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Mapper
public interface PageViewMapper extends BaseMapper<Pageview> {
}
