package com.wo.mapper;

import com.wo.domain.User;
import org.apache.ibatis.annotations.Select;

/**
* @author Wo
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-08-01 23:12:24
* @Entity com.wo.domain.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
