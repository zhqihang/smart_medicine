package qihang.smart.service.impl;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationOutput;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.MessageManager;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description: Api服务类
 * @author: zhqihang
 * @date: 2024/07/18
 */
@Service
public class ApiService {

    @Value("${ai-key}")
    private String apiKey;

    public String query(String queryMessage) {
        Constants.apiKey = apiKey;
        try {
            Generation gen = new Generation();
            MessageManager msgManager = new MessageManager(10);
            // promote
            Message systemMsg = Message.builder().role(Role.SYSTEM.getValue()).content("你是一个知晓医疗百科的智能医生，你只回答与疾病详情和治疗方法相关的问题，不要回答其他问题！").build();
            Message userMsg = Message.builder().role(Role.USER.getValue()).content(queryMessage).build();
            msgManager.add(systemMsg);
            msgManager.add(userMsg);
            QwenParam param = QwenParam.builder().model(Generation.Models.QWEN_TURBO).messages(msgManager.get()).resultFormat(QwenParam.ResultFormat.MESSAGE).build();
            GenerationResult result = gen.call(param);
            GenerationOutput output = result.getOutput();
            Message message = output.getChoices().get(0).getMessage();
            return message.getContent();
        } catch (Exception e) {
            return "网络错误，请联系管理员或稍后再试～";
        }
    }

}
