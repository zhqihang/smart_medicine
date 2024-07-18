package qihang.smart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qihang.smart.entity.History;

/**
 * @description: 浏览历史控制器
 * @author: zhqihang
 * @date: 2024/07/18
 */
@RestController
@RequestMapping("history")
public class HistoryController extends BaseController<History> {
}
