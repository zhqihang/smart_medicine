package qihang.smart.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import qihang.smart.entity.*;
import qihang.smart.utils.Assert;
import qihang.smart.utils.BeanUtil;
import qihang.smart.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.ArrayList;
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
        if (Assert.isEmpty(illness.getId())) {
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
        if (Assert.notEmpty(illness)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(illness);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) continue;
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

    /**
     * 分页查询疾病
     *
     * @param kind
     * @param illnessName
     * @param page
     * @return
     */
    public Map<String, Object> findIllness(Integer kind, String illnessName, Integer page) {

        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Illness> wrapper = new QueryWrapper<>();
        if (Assert.notEmpty(illnessName)) {
            wrapper.like("illness_name", illnessName)
                    .or()
                    .like("include_reason", illnessName)
                    .or()
                    .like("illness_symptom", illnessName)
                    .or()
                    .like("special_symptom", illnessName);
        }
        if (Assert.notEmpty(kind)) {
            if (illnessName != null) {
                wrapper.last("and (kind_id = " + kind +
                        ") order by create_time desc limit " + (page - 1) * 9 + "," + page * 9);
            } else {
                wrapper.eq("kind_id", kind);
                wrapper.orderByDesc("create_time");
                wrapper.last("limit" + (page - 1) * 9 + "," + page * 9);
            }
        } else {
            wrapper.orderByDesc("create_time");
            wrapper.last("limit" + (page - 1) * 9 + "," + page * 9);
        }

        int size = illnessMapper.selectMaps(wrapper).size();
        List<Map<String, Object>> list = illnessMapper.selectMaps(wrapper);
        list.forEach(l -> {
            Integer id = MapUtil.getInt(l, "id");
            Pageview pageInfo = pageViewMapper.selectOne(new QueryWrapper<Pageview>().eq("illness_id", id));
            l.put("kindName", "暂无归属类");
            l.put("create_name", MapUtil.getDate(l, "create_time"));
            l.put("pageview", pageInfo == null ? 0 : pageInfo.getPageviews());
            Integer kindId = MapUtil.getInt(l, "kind_id");
            if (Assert.notEmpty(kindId)) {
                IllnessKind illnessKind = illnessKindMapper.selectById(kindId);
                if (Assert.notEmpty(illnessKind)) {
                    l.put("kindName", illnessKind.getName());
                }
            }
        });
        map.put("illness", list);
        map.put("size", size < 9 ? 1 : size / 9 + 1);
        return map;
    }

    /**
     * 根据 id 查找疾病及其对应药物
     * @param id
     * @return
     */
    public Map<String, Object> findIllnessOne(Integer id) {
        Illness illness = illnessMapper.selectOne(new QueryWrapper<Illness>().eq("id", id));
        List<IllnessMedicine> illnessMedicines = illnessMedicineMapper.selectList(new QueryWrapper<IllnessMedicine>().eq("illness_id", id));
        List<Medicine> list = new ArrayList<>(4);
        Map<String, Object> map = new HashMap<>(4);
        Pageview illness_id = pageViewMapper.selectOne(new QueryWrapper<Pageview>().eq("illness_id", id));
        if (Assert.isEmpty(illness_id)) {
            illness_id = new Pageview();
            illness_id.setIllnessId(id);
            illness_id.setPageviews(1);
            pageViewMapper.insert(illness_id);
        } else {
            illness_id.setPageviews(illness_id.getPageviews() + 1);
            pageViewMapper.updateById(illness_id);
        }
        map.put("illness", illness);
        if (CollUtil.isNotEmpty(illnessMedicines)) {
            illnessMedicines.forEach(illnessMedicine -> {
                Medicine medicine = medicineMapper.selectOne(new QueryWrapper<Medicine>().eq("id", illnessMedicine.getMedicineId()));
                if (ObjectUtil.isNotNull(medicine)) {
                    list.add(medicine);
                }
            });
            map.put("medicine", list);
        }
        return map;
    }

    public Illness getOne(QueryWrapper<Illness> queryWrapper) {
        return illnessMapper.selectOne(queryWrapper);
    }
}
