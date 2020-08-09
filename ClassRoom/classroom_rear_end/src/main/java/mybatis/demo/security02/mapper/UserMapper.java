package mybatis.demo.security02.mapper;

import org.apache.ibatis.annotations.Mapper;
import mybatis.demo.security02.model.Role;
import mybatis.demo.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);
    List<Role> getUserRolesByUid(Long id);
    void addUser(User user);
    User getTeacherNameById(long uid);
    User getUserByUid(long uid);
}
