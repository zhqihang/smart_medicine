package qihang.smart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import qihang.smart.mapper.*;
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

    @Autowired
    protected IllnessKindMapper illnessKindMapper;

    @Autowired
    protected IllnessMedicineMapper illnessMedicineMapper;

    @Autowired
    protected PageViewMapper pageViewMapper;


}
