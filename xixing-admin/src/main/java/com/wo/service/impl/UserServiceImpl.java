package com.wo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wo.domain.User;
import com.wo.service.UserService;
import com.wo.mapper.UserMapper;
import com.wo.utils.R;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
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

    @Override
    public R<PageInfo<User>> listAll(int pageNum, int pageSize,String orderBy,String sortBy,String username) {
        String underlineCase = StrUtil.toUnderlineCase(orderBy);
        List<String> listDatabaseColumnName= userMapper.listDatabaseColumnName();
        if (ObjectUtil.isEmpty(orderBy)){
            PageInfo<User> userPageInfo = getUserPageInfo(pageNum, pageSize, username);
            return R.ok("成功",userPageInfo);
        }else {
            if (listDatabaseColumnName.contains(underlineCase)) {
                if (sortingJudgment(orderBy, sortBy)) {
                    return R.noContent("排序方式有误，应为ASC或者DESC");
                }
                PageInfo<User> pageInfo = getUserPageInfo(pageNum, pageSize, username);
                return R.ok("成功",pageInfo);
            } else {
                return R.noContent("对不起，您的排序字段名有误！");
            }
        }
    }

    private static boolean sortingJudgment(String orderBy, String sortBy) {
        if (ObjectUtil.isEmpty(sortBy)){
            PageHelper.orderBy(orderBy);
        }else if("ASC".equalsIgnoreCase(sortBy) || "DESC".equalsIgnoreCase(sortBy)){
            PageHelper.orderBy(orderBy +" "+ sortBy);
        }else {
            return true;
        }
        return false;
    }

    private PageInfo<User> getUserPageInfo(int pageNum, int pageSize, String username) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userVoList=userMapper.listAll(username);
        return new PageInfo<>(userVoList);
    }

}




