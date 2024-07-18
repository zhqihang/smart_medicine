package qihang.smart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import qihang.smart.entity.IllnessMedicine;
import qihang.smart.utils.Assert;
import qihang.smart.utils.BeanUtil;
import qihang.smart.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description: 疾病-药品服务类
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Service
public class IllnessMedicineService extends BaseService<IllnessMedicine> {

    @Override
    public List<IllnessMedicine> query(IllnessMedicine illnessMedicine) {
        QueryWrapper<IllnessMedicine> wrapper = new QueryWrapper();
        if (Assert.notEmpty(illnessMedicine)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(illnessMedicine);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return illnessMedicineMapper.selectList(wrapper);
    }

    @Override
    public List<IllnessMedicine> all() {
        return query(null);
    }

    @Override
    public IllnessMedicine save(IllnessMedicine illnessMedicine) {
        if (Assert.isEmpty(illnessMedicine.getId())) {
            illnessMedicineMapper.insert(illnessMedicine);
        } else {
            illnessMedicineMapper.updateById(illnessMedicine);
        }
        return illnessMedicineMapper.selectById(illnessMedicine.getId());
    }

    @Override
    public IllnessMedicine get(Serializable id) {
        return illnessMedicineMapper.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return illnessMedicineMapper.deleteById(id);
    }

}
