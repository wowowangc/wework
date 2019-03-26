package com.hogs.wework.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class MemberTest {

    Member member;

    @BeforeEach
    public void setUp() {
        member = new Member();
    }

    @Test
    public void testCreate() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid", "wang" + member.rand);
        map.put("name", "王" + member.rand);
        map.put("alias", "小王" + member.rand);
        map.put("mobile", "159" + member.rand.substring(0,8));
        map.put("email", "159" + member.rand.substring(0,8) + "@qq.com");
        member.create(map);

    }
}