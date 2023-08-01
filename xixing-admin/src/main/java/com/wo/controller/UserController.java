package com.wo.controller;

import com.wo.domain.User;
import com.wo.service.UserService;
import com.wo.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Wo
 * @date 2023/7/23 7:54
 * @description 用户controller
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 查询单个
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("{id}")
    public R<User> getUser(@PathVariable Long id){
        return userService.selectByPrimaryKey(id);
    }

    /**
     * 添加用户
     * @param user 用户信息
     * @return 成功或失败
     */
    @PostMapping
    public R<User> saveUser(@RequestBody User user){
        return userService.insert(user);
    }

    /**
     * 选择性添加用户
     * @param user 用户信息
     * @return 成功或失败
     */
    @PostMapping("selective")
    public R<User> saveUserSelective(@RequestBody User user){
        return userService.insertSelective(user);
    }
    @PutMapping("selective/{id}")
    public R<User> updateUserSelective(@PathVariable Long id, @RequestBody User user){
        return userService.updateByPrimaryKeySelective(id,user);
    }
    @PutMapping("{id}")
    public R<User> updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateByPrimaryKey(id,user);
    }
    @DeleteMapping("{id}")
    public R<User> removeUser(@PathVariable Long id){
        return userService.deleteByPrimaryKey(id);
    }
}
