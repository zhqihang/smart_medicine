package qihang.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qihang.smart.dto.RespResult;
import qihang.smart.entity.User;
import qihang.smart.service.impl.UserService;

/**
 * @description: 用户控制类
 * @author: zhqihang
 * @date: 2024/07/17
 */

@RestController
@RequestMapping(value = "user")
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    /**
     * 用户修改资料
     * @param user
     * @return
     */
    @PostMapping("/saveProfile")
    public RespResult saveProfile(User user) {
        if (user == null) return RespResult.fail("保存对应不能为空");
        // 保存用户
        user = userService.save(user);
        session.setAttribute("loginUser", user);
        return RespResult.success("保存成功");
    }

    /**
     * 用户修改密码
     * @param oldPass
     * @param newPass
     * @return
     */
    @PostMapping("/savePassword")
    public RespResult savePassword(String oldPass, String newPass) {
        if (!loginUser.getUserPwd().equals(oldPass))
            return RespResult.fail("原始密码错误");
        // 更新密码
        loginUser.setUserPwd(newPass);
        loginUser = userService.save(loginUser);
        session.setAttribute("loginUser", loginUser);
        return RespResult.success("保存成功");
    }
}
