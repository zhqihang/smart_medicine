package qihang.smart.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description: 用户实体类
 * @author: zhqihang
 * @date: 2024/07/16
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user") // MybatisPlus注解
public class User {

    private Integer id;

    private String userAccount;

    private String userName;

    private String userPwd;

    private Integer userAge;

    private String userSex; // 用户性别

    private String userEmail;

    private String userTel;

    private Integer roleStatus; // 用户角色 1管理员 0普通用户

    private String imgPath; // 头像地址

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
