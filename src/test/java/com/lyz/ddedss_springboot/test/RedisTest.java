package com.lyz.ddedss_springboot.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void putString() {
        // redisTemplate 操作不同的数据类型，api和我们的指令是一样的
        // opsForValue 操作字符串 类似String
        // opsForList 操作List 类似List
        // opsForSet
        // opsForHash
        // opsForZSet
        // opsForGeo
        // opsForHyperLogLog

        stringRedisTemplate.opsForValue().set("username", "password", 10, TimeUnit.SECONDS);

        System.out.println(stringRedisTemplate.opsForValue().get("username"));

    }

    @Test
    public void testList() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        String startTime = simpleDateFormat.format(System.currentTimeMillis());
        // 设置过期时间
//        stringRedisTemplate.expire("evaluate", 10, TimeUnit.SECONDS);
        // 添加内容到集合
//        stringRedisTemplate.opsForSet().add("evaluate", startTime);
//        stringRedisTemplate.opsForSet().add("evaluate", "1");
//        stringRedisTemplate.opsForSet().add("evaluate", "1");
//        stringRedisTemplate.opsForSet().add("evaluate", "2");
//        stringRedisTemplate.opsForSet().add("evaluate", "3");
        // 判断元素是否在集合中
//        System.out.println(stringRedisTemplate.opsForSet().isMember("evaluate", "1"));
//        System.out.println(stringRedisTemplate.opsForSet().isMember("evaluate", "9"));
        // 判断集合是否存在
        int evaluate = stringRedisTemplate.opsForSet().members("evaluate").size();
        System.out.println(evaluate == 0);

    }

    @Test
    public void testGetNull() {
        String b200113 = stringRedisTemplate.opsForValue().get("B200113");
        System.out.println(b200113);
    }

    @Test
    public void testAdd() {
        Integer roleId = 23;
        stringRedisTemplate.opsForSet().add("evaluate", roleId.toString());
    }

    /**
     * redis加入string测试
     */
    @Test
    public void addString() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        stringRedisTemplate.opsForValue().set(uuid, "2");
    }

    /**
     * keys *
     */
    @Test
    public void keys() {
        Set<String> keys = stringRedisTemplate.keys("*");
        for (String key : keys) {
            if (key.length() == 32) {
                System.out.println(stringRedisTemplate.opsForValue().get(key));
            }
        }
    }

    /**
     * 获取过期时间
     */
    @Test
    public void get(){
        Long expire = stringRedisTemplate.opsForValue().getOperations().getExpire("761c09f7d29345ada0bc96b5501635aa");
        System.out.println(expire);
    }

}
