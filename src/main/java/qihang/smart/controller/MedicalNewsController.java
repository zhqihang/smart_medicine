package qihang.smart.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qihang.smart.entity.MedicalNews;

/**
 * @description: 医疗咨询控制类
 * @author: zhqihang
 * @date: 2024/07/18
 */

@RestController
@RequestMapping("medical_news")
public class MedicalNewsController extends BaseController<MedicalNews> {
}
