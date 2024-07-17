package qihang.smart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import qihang.smart.entity.IllnessKind;
import qihang.smart.utils.BeanUtil;
import qihang.smart.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description: 疾病类型服务类
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Service
public class IllnessKindService extends BaseService<IllnessKind> {

    /**
     * 查询疾病类型
     * @param illnessKind
     * @return
     */
    @Override
    public List<IllnessKind> query(IllnessKind illnessKind) {
        QueryWrapper<IllnessKind> wrapper = new QueryWrapper();
        if (illnessKind != null) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(illnessKind);
            for (String key : bean2Map.keySet()) {
                if (bean2Map.get(key) == null) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return illnessKindMapper.selectList(wrapper);
    }

    /**
     * 获取所有疾病类型
     * @return
     */
    @Override
    public List<IllnessKind> all() {
        return query(null);
    }

    /**
     * 新增、更新疾病类型
     * @param illnessKind
     * @return
     */
    @Override
    public IllnessKind save(IllnessKind illnessKind) {
        if (illnessKind.getId() == null) {
            illnessKindMapper.insert(illnessKind);
        } else {
            illnessKindMapper.updateById(illnessKind);
        }
        return illnessKindMapper.selectById(illnessKind.getId());
    }

    /**
     * 根据 id 获取疾病
     * @param id
     * @return
     */
    @Override
    public IllnessKind get(Serializable id) {
        return illnessKindMapper.selectById(id);
    }

    /**
     * 根据 id 删除疾病类型
     * @param id
     * @return
     */
    @Override
    public int delete(Serializable id) {
        return illnessKindMapper.deleteById(id);
    }

    public List<IllnessKind> findList() {
        return illnessKindMapper.selectList(new QueryWrapper<IllnessKind>());
    }
}
