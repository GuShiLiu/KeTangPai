package mybatis.demo.dao;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LoginUpMapper {
    void addUserRole(long uid , long rid);
}
