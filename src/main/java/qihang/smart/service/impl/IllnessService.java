package qihang.smart.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import qihang.smart.entity.Illness;
import qihang.smart.utils.BeanUtil;
import qihang.smart.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 疾病服务类
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Service
public class IllnessService extends BaseService<Illness> {

    /**
     * 保存药品
     *
     * @param illness
     * @return
     */
    @Override
    public Illness save(Illness illness) {
        if (illness.getId() == null) {
            illnessMapper.insert(illness);
        } else {
            illnessMapper.updateById(illness);
        }
        return illnessMapper.selectById(illness.getId());
    }

    /**
     * 根据 id 获取疾病
     *
     * @param id
     * @return
     */
    @Override
    public Illness get(Serializable id) {
        return illnessMapper.selectById(id);
    }

    /**
     * 根据 id 删除疾病
     *
     * @param id
     * @return
     */
    @Override
    public int delete(Serializable id) {
        return illnessMapper.deleteById(id);
    }

    /**
     * 查询疾病
     *
     * @param illness
     * @return
     */
    @Override
    public List<Illness> query(Illness illness) {
        QueryWrapper<Illness> wrapper = new QueryWrapper<>();
        if (illness != null) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(illness);
            for (String key : bean2Map.keySet()) {
                if (bean2Map.get(key) == null) continue;
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return illnessMapper.selectList(wrapper);
    }

    /**
     * 查询所有药品
     *
     * @return
     */
    @Override
    public List<Illness> all() {
        return query(null);
    }
}
