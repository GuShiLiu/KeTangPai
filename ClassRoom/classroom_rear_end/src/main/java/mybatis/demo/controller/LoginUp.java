package mybatis.demo.controller;


import mybatis.demo.entity.User;
import mybatis.demo.service.LoginUpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/loginUp")
public class LoginUp {

    @Resource
    private LoginUpService loginUpService;

    @GetMapping("/addTeacher")
    public void addTeacher(User user){
        loginUpService.addTeacher(user);
    }

    @GetMapping("/addStudent")
    public void addStudent(User user){
        loginUpService.addStudent(user);
    }
}
