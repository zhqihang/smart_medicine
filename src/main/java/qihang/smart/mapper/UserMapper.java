package qihang.smart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import qihang.smart.entity.User;

/**
 * @description: 用户Mapper接口
 * @author: zhqihang
 * @date: 2024/07/16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
