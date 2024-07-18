package qihang.smart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import qihang.smart.entity.User;
import qihang.smart.utils.Assert;
import qihang.smart.utils.BeanUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static qihang.smart.utils.VariableNameUtils.humpToLine;

/**
 * @description: 用户服务类
 * @author: zhqihang
 * @date: 2024/07/16
 */

@Service
public class UserService extends BaseService<User> {

    @Override
    public User save(User user) {
        if (Assert.isEmpty(user.getId())) {
            userMapper.insert(user);
        } else {
            userMapper.updateById(user);
        }
        return userMapper.selectById(user.getId());
    }

    @Override
    public User get(Serializable id) {
        return userMapper.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return userMapper.deleteById(id);
    }

    @Override
    public List<User> query(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(user)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(user);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(humpToLine(key), bean2Map.get(key));
            }
        }
        return userMapper.selectList(wrapper);
    }

    @Override
    public List<User> all() {
        return query(null);
    }
}
