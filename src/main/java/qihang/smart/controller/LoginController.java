package qihang.smart.controller;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qihang.smart.dto.RespResult;
import qihang.smart.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description: 登录控制器
 * @author: zhqihang
 * @date: 2024/07/17
 */
@RestController
@RequestMapping("login")
public class LoginController extends BaseController<User> {

    /**
     * 用户注册
     *
     */
    @PostMapping("/register")
    public RespResult register(User user, String code) {
        String email = user.getUserEmail();
        if (email == null) return RespResult.fail("邮箱不能为空");
        Map<String, Object> codeData = (Map<String, Object>) session.getAttribute("EMAIL_CODE" + email);
        if (codeData == null) return RespResult.fail("尚未发送验证码");
        String sentCode = (String) codeData.get("code");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) codeData.get("time"));
        calendar.add(Calendar.MINUTE, 5);
        if (System.currentTimeMillis() > calendar.getTime().getTime()) {
            // session.removeAttribute("EMAIL_CODE" + email);
            return RespResult.fail("验证码过期");
        }
        if (!sentCode.equals(code))
            return RespResult.fail("验证码错误");

        // 根据用户账号查询
        List<User> query = userService.query(User.builder().userAccount(user.getUserAccount()).build());
        if (query != null) return RespResult.fail("账号已被注册");

        user.setRoleStatus(0); // 普通用户
        user.setImgPath("https://smart-medical-system.oss-cn-guangzhou.aliyuncs.com/%E9%BB%98%E8%AE%A4%E5%A4%B4%E5%83%8F"); // 初始默认头像链接
        user = userService.save(user);
        session.setAttribute("loginUser", user);
        return RespResult.success("注册成功", user);
    }

    /**
     * 用户登录
     *
     */
    @PostMapping("/login")
    public RespResult login(User user) {
        List<User> users = userService.query(user);
        if (users != null && users.size() != 0) {
            session.setAttribute("loginUser", users.get(0));
            return RespResult.success("登录成功");
        }
        if (userService.query(User.builder().userAccount(user.getUserAccount()).build()) == null)
            return RespResult.fail("账户未注册");
        return RespResult.fail("密码错误");
    }

    /**
     * 发送邮箱验证码
     *
     */
    @PostMapping("/sendEmailCode")
    public RespResult sendEmailCode(String email, Map<String, Object> map) {
        if (StrUtil.isEmpty(email))
            return RespResult.fail("邮箱不能为空");
        String verifyCode = emailClient.sendEmailCode(email);
        map.put("email", email);
        map.put("code", verifyCode);
        map.put("time", new Date());
        session.setAttribute("EMAIL_CODE" + email, map);
        return RespResult.success("发送成功");
    }
}
