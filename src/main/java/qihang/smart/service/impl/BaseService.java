package qihang.smart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import qihang.smart.entity.Illness;
import qihang.smart.mapper.IllnessMapper;
import qihang.smart.mapper.MedicineMapper;
import qihang.smart.mapper.UserMapper;
import qihang.smart.service.IService;

/**
 * @description: 基础服务类
 * @author: zhqihang
 * @date: 2024/07/16
 */
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected MedicineMapper medicineMapper;

    @Autowired
    protected IllnessMapper illnessMapper;


}