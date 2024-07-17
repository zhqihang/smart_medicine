package qihang.smart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import qihang.smart.entity.Illness;


/**
 * @description: 疾病Mapper接口
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Mapper
public interface IllnessMapper extends BaseMapper<Illness> {
}
