package com.wo.controller;

import com.wo.domain.User;
import com.wo.service.UserService;
import com.wo.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Wo
 * @date 2023/7/29 22:57
 * @description 公共控制器
 */
@RestController
@RequestMapping("public")
public class PublicController {
    @Resource
    private UserService userService;
}
