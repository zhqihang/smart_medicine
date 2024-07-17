package qihang.smart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qihang.smart.entity.Illness;

/**
 * @description: 疾病控制类
 * @author: zhqihang
 * @date: 2024/07/17
 */
@RestController
@RequestMapping("illness")
public class IllnessController extends BaseController<Illness> {
}
