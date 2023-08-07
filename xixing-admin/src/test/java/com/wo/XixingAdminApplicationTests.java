package com.wo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.wo.domain.User;
import com.wo.service.UserService;
import com.wo.utils.R;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class XixingAdminApplicationTests {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserService userService;
    @Test
    void contextLoads() {
        R<PageInfo<User>> pageInfoR = userService.listAll(1, 10,null,null,"");
        System.out.println(pageInfoR);
    }
    @Test
    void contextLoads1() {
        R<PageInfo<User>> pageInfoR = userService.listAll(1, 10,null,null,"");
        System.out.println(pageInfoR);
        List<User> list = pageInfoR.getData().getList();
        for (User item:list) {
            redisTemplate.opsForValue().set(item.getUsername(), item.getPassword());
            String a = (String)redisTemplate.opsForValue().get(item.getUsername());
            System.out.println(a);
        }

    }
    private static final ObjectMapper mapper = new ObjectMapper();
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void saveUserString() throws JsonProcessingException {
        long a = System.nanoTime();
        R<PageInfo<User>> pageInfoR = userService.listAll(1, 10,null,null,"");
        long b = System.nanoTime();
        System.out.println("数据库查询时间："+(b-a));
        List<User> list = pageInfoR.getData().getList();
        long c = System.nanoTime();
        // 创建对象
        for (User user:list) {
            // 手动序列化
            String json = mapper.writeValueAsString(user);
            // 写入数据
            long e = System.nanoTime();
            stringRedisTemplate.opsForValue().set(user.getUsername(),json);
            long f = System.nanoTime();
            System.out.println("写数据时间："+(f-e));
            // 获取数据
            long g = System.nanoTime();
            String jsonUser = stringRedisTemplate.opsForValue().get(user.getUsername());
            long h = System.nanoTime();
            System.out.println("读数据时间："+(h-g));
            // 手动反序列化
            User readValue = mapper.readValue(jsonUser, User.class);
            System.out.println("user:"+readValue);
        }
        long d = System.nanoTime();
        System.out.println("遍历带打印数据时间："+(d-c));
        // 手动序列化
        String json = mapper.writeValueAsString(list);
        // 写入数据
        stringRedisTemplate.opsForValue().set("list",json);
        // 获取数据
        String jsonUser = stringRedisTemplate.opsForValue().get("list");
        // 手动反序列化
//        User readValue = mapper.readValue(jsonUser, User.class);
        List<User> userList = null;
        try {
            userList = mapper.readValue(jsonUser, new TypeReference<List<User>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (userList != null) {
            for (User user : userList) {
                System.out.println("user: " + user);
            }
        }



    }

}
