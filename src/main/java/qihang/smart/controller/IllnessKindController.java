package qihang.smart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qihang.smart.entity.IllnessKind;

/**
 * @description: 疾病类型控制类
 * @author: zhqihang
 * @date: 2024/07/17
 */
@RestController
@RequestMapping("illness_kind")
public class IllnessKindController extends BaseController<IllnessKind> {
}
