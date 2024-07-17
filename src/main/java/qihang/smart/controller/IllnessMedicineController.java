package qihang.smart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qihang.smart.entity.IllnessMedicine;

/**
 * @description: 疾病-药品控制类
 * @author: zhqihang
 * @date: 2024/07/17
 */
@RestController
@RequestMapping("illness_medicine")
public class IllnessMedicineController extends BaseController<IllnessMedicine> {
}
