package qihang.smart.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qihang.smart.component.EmailClient;
import qihang.smart.dto.RespResult;
import qihang.smart.entity.User;
import qihang.smart.service.impl.UserService;
import qihang.smart.service.BaseSevice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description: TODO
 * @author: zhqihang
 * @date: 2024/07/17
 */
public class BaseController<T> {

    @Autowired
    protected UserService userService;
    
    @Autowired
    protected EmailClient emailClient;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User loginUser;
}
