package qihang.smart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import qihang.smart.entity.Medicine;
import qihang.smart.utils.Assert;
import qihang.smart.utils.BeanUtil;
import qihang.smart.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 药品服务类
 * @author: zhqihang
 * @date: 2024/07/17
 */

@Service
public class MedicineService extends BaseService<Medicine> {

    /**
     * 保存药品
     * @param medicine
     * @return
     */
    @Override
    public Medicine save(Medicine medicine) {
        if (Assert.isEmpty(medicine.getId())) {
            medicineMapper.insert(medicine);
        } else {
            medicineMapper.updateById(medicine);
        }
        return medicineMapper.selectById(medicine.getId());
    }

    /**
     * 根据 id 获取药品
     * @param id
     * @return
     */
    @Override
    public Medicine get(Serializable id) {
        return medicineMapper.selectById(id);
    }

    /**
     * 根据 id 删除药品
     * @param id
     * @return
     */
    @Override
    public int delete(Serializable id) {
        return medicineMapper.deleteById(id);
    }

    /**
     * 查询药品
     * @param medicine
     * @return
     */
    @Override
    public List<Medicine> query(Medicine medicine) {
        QueryWrapper<Medicine> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(medicine)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(medicine);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) continue;
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return medicineMapper.selectList(wrapper);
    }

    @Override
    public List<Medicine> all() {
        return query(null);
    }

    /**
     * 获取单页药品列表
     * @param nameValue
     * @param page
     * @return
     */
    public Map<String, Object> getMedicineList(String nameValue, Integer page) {
        List<Medicine> medicineList = null;
        Map<String, Object> map = new HashMap<>(4);
        if (nameValue != null) {
            medicineList = medicineMapper.selectList(new QueryWrapper<Medicine>().
                    like("medicine_name", nameValue)
                    .or().like("keyword", nameValue)
                    .or().like("medicine_effect", nameValue)
                    .last("limit " + (page - 1) * 9 + "," + page * 9));
        } else {
            medicineList = medicineMapper.selectList(new QueryWrapper<Medicine>()
                    .last("limit " + (page - 1) * 9 + "," + page * 9));
        }
        map.put("medicineList", medicineList);
        map.put("size", medicineList.size() < 9 ? 1 : medicineList.size() / 9 + 1);
        return map;
    }
}
