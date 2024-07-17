package qihang.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qihang.smart.component.EmailClient;
import qihang.smart.dto.RespResult;
import qihang.smart.entity.User;
import qihang.smart.service.impl.BaseService;
import qihang.smart.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description: 基础控制类
 * @author: zhqihang
 * @date: 2024/07/17
 */
public class BaseController<T> {

    @Autowired(required = false)
    protected BaseService<T> service;

    @Autowired
    protected UserService userService;

    @Autowired
    protected EmailClient emailClient;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User loginUser;




    /**
     * 保存、修改
     *
     * @param obj 目标对象
     * @return 响应结果
     */
    @ResponseBody
    @PostMapping("save")
    public RespResult save(T obj) {
        if (obj == null) {
            return RespResult.fail("保存对象不能为空");
        }
        obj = service.save(obj);
        return RespResult.success("保存成功", obj);
    }

    /**
     * 删除
     *
     * @param id 主键ID
     * @return 响应结果
     */
    @ResponseBody
    @PostMapping("/delete")
    public RespResult delete(Integer id) {
        if (id == null) {
            return RespResult.fail("删除ID不能为空");
        }
        if (service.delete(id) == 0) {
            T t = service.get(id);
            if (t == null) {
                return RespResult.notFound("数据不存在");
            }
            return RespResult.fail("删除失败");
        }
        return RespResult.success("删除成功");
    }


    /**
     * 在每个子类方法调用之前先调用
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        loginUser = (User) session.getAttribute("loginUser");
        // session.setAttribute("kindList", illnessKindService.findList());
    }
}
