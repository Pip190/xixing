package com.wo.service;


import com.github.pagehelper.PageInfo;
import com.wo.domain.User;
import com.wo.utils.R;

import java.util.List;

/**
* @author Wo
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-07-30 00:28:43
*/
public interface UserService {
    R<User> deleteByPrimaryKey(Long id);

    R<User> insert(User record);

    R<User> insertSelective(User record);

    R<User> selectByPrimaryKey(Long id);

    R<User> updateByPrimaryKeySelective(Long id, User record);

    R<User> updateByPrimaryKey(Long id, User record);

    R<PageInfo<User>> listAll(int pageNum, int pageSize,String orderBy,String sortBy,String username);

}
