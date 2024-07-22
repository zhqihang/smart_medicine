package qihang.smart.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import qihang.smart.dto.RespResult;
import qihang.smart.entity.User;

/**
 * @description: 消息发送控制类
 * @author: zhqihang
 * @date: 2024/07/18
 */
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController<User> {

    /**
     * 发送消息
     */
    @PostMapping("/query")
    public RespResult query(String content) {
        String result = apiService.query(content);
        return RespResult.success(result);
    }
}
