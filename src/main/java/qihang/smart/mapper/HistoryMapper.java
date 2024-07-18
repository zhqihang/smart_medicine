package qihang.smart.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import qihang.smart.entity.History;

/**
 * @description: 浏览历史 Mapper 接口
 * @author: zhqihang
 * @date: 2024/07/18
 */
@Mapper
@TableName("history")
public interface HistoryMapper extends BaseMapper<History> {
}
