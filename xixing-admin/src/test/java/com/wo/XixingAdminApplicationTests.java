package com.wo;

import com.github.pagehelper.PageInfo;
import com.wo.domain.User;
import com.wo.service.UserService;
import com.wo.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class XixingAdminApplicationTests {
    @Resource
    private UserService userService;
    @Test
    void contextLoads() {
    }
    @Test
    void contextLoads1() {
        R<PageInfo<User>> pageInfoR = userService.listAll(1, 10,null,null,"高");
        System.out.println(pageInfoR);
    }

}
