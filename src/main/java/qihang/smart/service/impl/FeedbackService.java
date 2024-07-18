package qihang.smart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import qihang.smart.entity.Feedback;
import qihang.smart.utils.Assert;
import qihang.smart.utils.BeanUtil;
import qihang.smart.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description: 反馈服务类
 * @author: zhqihang
 * @date: 2024/07/18
 */
@Service
public class FeedbackService extends BaseService<Feedback> {

    @Override
    public List<Feedback> query(Feedback feedback) {
        QueryWrapper<Feedback> wrapper = new QueryWrapper();
        if (Assert.notEmpty(feedback)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(feedback);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return feedbackMapper.selectList(wrapper);
    }

    @Override
    public List<Feedback> all() {
        return query(null);
    }

    @Override
    public Feedback save(Feedback feedback) {
        if (Assert.isEmpty(feedback.getId())) {
            feedbackMapper.insert(feedback);
        } else {
            feedbackMapper.updateById(feedback);
        }
        return feedbackMapper.selectById(feedback.getId());
    }

    @Override
    public Feedback get(Serializable id) {
        return feedbackMapper.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return feedbackMapper.deleteById(id);
    }
}
