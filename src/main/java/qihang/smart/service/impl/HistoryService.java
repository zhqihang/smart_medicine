package qihang.smart.service.impl;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import qihang.smart.entity.History;
import qihang.smart.entity.IllnessKind;
import qihang.smart.utils.BeanUtil;
import qihang.smart.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 浏览历史控制类
 * @author: zhqihang
 * @date: 2024/07/18
 */
@Service
@RequestMapping("/history")
public class HistoryService extends BaseService<History>{
    @Override
    public List<History> query(History o) {
        QueryWrapper<History> wrapper = new QueryWrapper();
        if (o != null) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (bean2Map.get(key) == null) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return historyMapper.selectList(wrapper);
    }

    @Override
    public List<History> all() {
        return query(null);
    }

    @Override
    public History save(History history) {
        if (history.getId() == null) {
            historyMapper.insert(history);
        } else {
            historyMapper.updateById(history);
        }
        return historyMapper.selectById(history.getId());
    }

    @Override
    public History get(Serializable id) {
        return historyMapper.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return historyMapper.deleteById(id);
    }

    public boolean insetOne(Integer uid, Integer type, String nameValue) {
        History history = new History();
        history.setUserId(uid).setKeyword(nameValue).setOperateType(type);
        return historyMapper.insert(history) > 0;
    }

    public List<Map<String, Object>> findList(Integer userId) {
        List<History> list = historyMapper.selectList(new QueryWrapper<History>().eq("user_id", userId)
                .orderByDesc("create_time"));
        List<History> histories = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(History::getKeyword))), LinkedList::new));
        histories.sort((h1, h2) -> -h1.getCreateTime().compareTo(h2.getCreateTime()));
        List<History> historyList = histories.stream().limit(10).collect(Collectors.toList());
        System.out.println(histories.size());
        List<Map<String, Object>> mapList = new LinkedList<>();
        historyList.forEach(his -> {
            Map<String, Object> map = cn.hutool.core.bean.BeanUtil.beanToMap(his);
            Integer operateType = MapUtil.getInt(map, "operateType");
            if (operateType == 1) {
                List<String> keyword = Arrays.asList((MapUtil.getStr(map, "keyword")).split(","));
                IllnessKind illnessKind = illnessKindMapper.selectById(Integer.valueOf(keyword.get(0)));
                map.put("kind", illnessKind.getId());
                map.put("nameValue", keyword.get(1));
                map.put("searchValue", illnessKind.getName() + ("无".equals(keyword.get(1)) ? "" : ("|" + keyword.get(1))));
            } else if (operateType == 2) {
                map.put("nameValue", MapUtil.getStr(map, "keyword"));
                map.put("kind", "无");
                map.put("searchValue", MapUtil.getStr(map, "keyword"));
            } else if (operateType == 3) {
                map.put("nameValue", MapUtil.getStr(map, "keyword"));
                map.put("searchValue", MapUtil.getStr(map, "keyword"));
                map.put("kind", "无");
            }
            mapList.add(map);
        });
        return mapList;
    }
}
