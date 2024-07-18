package qihang.smart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import qihang.smart.entity.Feedback;

/**
 * @description: 反馈 Mapper 接口
 * @author: zhqihang
 * @date: 2024/07/18
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {
}
