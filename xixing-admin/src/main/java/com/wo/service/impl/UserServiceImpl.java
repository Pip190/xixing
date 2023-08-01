package com.wo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.wo.domain.User;
import com.wo.service.UserService;
import com.wo.mapper.UserMapper;
import com.wo.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
* @author Wo
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-07-30 00:28:43
*/
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    @Override
    public R<User> deleteByPrimaryKey(Long id) {
        int deleteByPrimaryKey = this.userMapper.deleteByPrimaryKey(id);
        if (deleteByPrimaryKey>0){
            return R.ok("删除成功");
        }
        return R.noContent("删除失败");
    }

    @Override
    public R<User> insert(User record) {
        int insert = this.userMapper.insert(record);
        if (insert>0){
            return R.created("添加成功",record);
        }
        return R.badRequest("添加失败");
    }

    @Override
    public R<User> insertSelective(User record) {
        int insertSelective = this.userMapper.insertSelective(record);
        if (insertSelective>0){
            return R.created("添加成功",record);
        }
        return R.badRequest("添加失败");
    }

    @Override
    public R<User> selectByPrimaryKey(Long id) {
        User user = this.userMapper.selectByPrimaryKey(id);
        if (!ObjectUtils.isEmpty(user)){
            return R.ok("查询成功",user);
        }
        return R.noContent("资源不存在");
    }

    @Override
    public R<User> updateByPrimaryKeySelective(Long id, User source) {
        User user = this.userMapper.selectByPrimaryKey(id);
        if (Objects.nonNull(user)){
            CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true).setIgnoreProperties("id");
            BeanUtil.copyProperties(source,user,copyOptions);
            int updateByPrimaryKeySelective = this.userMapper.updateByPrimaryKeySelective(user);
            if (updateByPrimaryKeySelective>0){
                return R.ok("修改成功");
            }
            return R.noContent("修改失败");
        }
        return R.notFound("修改失败");
    }

    @Override
    public R<User> updateByPrimaryKey(Long id, User source) {
        User user = this.userMapper.selectByPrimaryKey(id);
        if (Objects.nonNull(user)){
            BeanUtil.copyProperties(source,user,"id");
            int updateByPrimaryKey = this.userMapper.updateByPrimaryKey(user);
            if (updateByPrimaryKey>0){
                return R.ok("修改成功");
            }
        }
        return R.noContent("修改失败");
    }

}




