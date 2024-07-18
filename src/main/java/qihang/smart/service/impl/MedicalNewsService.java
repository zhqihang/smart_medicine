package qihang.smart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import qihang.smart.entity.MedicalNews;
import qihang.smart.utils.Assert;
import qihang.smart.utils.BeanUtil;
import qihang.smart.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description: 医疗咨询服务类
 * @author: zhqihang
 * @date: 2024/07/18
 */
@Service
public class MedicalNewsService extends BaseService<MedicalNews> {

    @Override
    public List<MedicalNews> query(MedicalNews medicalNews) {
        QueryWrapper<MedicalNews> wrapper = new QueryWrapper();
        if (Assert.notEmpty(medicalNews)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(medicalNews);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return medicalNewsMapper.selectList(wrapper);
    }

    @Override
    public List<MedicalNews> all() {
        return query(null);
    }

    @Override
    public MedicalNews save(MedicalNews medicalNews) {
        if (Assert.isEmpty(medicalNews.getId())) {
            medicalNewsMapper.insert(medicalNews);
        } else {
            medicalNewsMapper.updateById(medicalNews);
        }
        return medicalNewsMapper.selectById(medicalNews.getId());
    }

    @Override
    public MedicalNews get(Serializable id) {
        return medicalNewsMapper.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return medicalNewsMapper.deleteById(id);
    }
}
