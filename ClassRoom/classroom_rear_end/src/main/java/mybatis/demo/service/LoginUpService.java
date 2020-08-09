package mybatis.demo.service;

import mybatis.demo.dao.LoginUpMapper;
import mybatis.demo.entity.User;
import mybatis.demo.security02.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginUpService {
    @Autowired
    private LoginUpMapper loginUpMapper;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    //增加一个老师
    public void addTeacher(User user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);//数据库加密存储密码
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.addUser(user);
        long uid = userMapper.loadUserByUsername(user.getUsername()).getuid();
        loginUpMapper.addUserRole(uid,2);
    }
    //增加一个学生
    public void addStudent(User user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.addUser(user);
        long uid = userMapper.loadUserByUsername(user.getUsername()).getuid();
        loginUpMapper.addUserRole(uid,3);
    }

}
