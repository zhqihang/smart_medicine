package qihang.smart.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import qihang.smart.component.OssClient;
import qihang.smart.dto.RespResult;
import qihang.smart.entity.User;

import java.io.IOException;

/**
 * @description: 文件上传 控制类
 * @author: zhqihang
 * @date: 2024/07/18
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController<User> {

    @Autowired
    private OssClient ossClient;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public RespResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        String url = ossClient.upload(file, String.valueOf(loginUser.getId()));
        if (url == null) {
            return RespResult.fail("上传失败", url);
        }
        return RespResult.success("上传成功", url);
    }

}
