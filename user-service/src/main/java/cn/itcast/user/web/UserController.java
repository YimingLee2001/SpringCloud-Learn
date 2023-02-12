package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
// @RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    // 热更新第一种方案，RefreshScope
    // Value注释的作用是将配置文件的属性读出来
    // 这里似乎中文在配置中会出现问题，解决方法是加上"-Dfile.encoding=utf8"
    // @Value("${pattern.dateformat}")
    // private String dateformat;

    // 热更新第二种方案，ConfigurationProperties
    @Autowired
    private PatternProperties properties;

    /**
     * 测试Nacoa热更新配置功能，这里的配置是时间格式
     * 
     * @return
     */
    @GetMapping("now")
    public String now() {
        // 第一种方案
        // return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));

        // 第二种方案
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat()));
    }

    /**
     * 测试多环境共享热更新配置
     * 
     * @return
     */
    @GetMapping("prop")
    public PatternProperties properties2() {
        return properties;
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }
}
