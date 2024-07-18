package qihang.smart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import qihang.smart.entity.Feedback;

/**
 * @description: 反馈控制类
 * @author: zhqihang
 * @date: 2024/07/18
 */
@Controller
@RequestMapping("feedback")
public class FeedbackController extends BaseController<Feedback> {
}
