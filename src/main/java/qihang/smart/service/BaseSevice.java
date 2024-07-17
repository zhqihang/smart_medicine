package qihang.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import qihang.smart.mapper.UserMapper;

/**
 * @description: 基础服务类
 * @author: zhqihang
 * @date: 2024/07/16
 */
public abstract class BaseSevice<T> implements IService<T> {

    @Autowired
    protected UserMapper userMapper;
}
