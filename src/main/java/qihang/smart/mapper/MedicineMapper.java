package qihang.smart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import qihang.smart.entity.Medicine;

import java.util.List;
import java.util.Map;

/**
 * @description: 药品Mapper接口
 * @author: zhqihang
 * @date: 2024/07/17
 */
@Mapper
public interface MedicineMapper extends BaseMapper<Medicine> {

    /**
     * 根据疾病查询药物
     */
    List<Map<String, Object>> findMedicineList(Integer illnessId);
}
