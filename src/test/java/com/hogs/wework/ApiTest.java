package com.hogs.wework;

import okio.HashingSource;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class ApiTest {

    @Test
    void send() {
        Api api = new Api();
        api.send().then().log().all().statusCode(200);
    }


    @Test
    void templateFromYaml() {
        Api api = new Api();
        HashMap<String, Object> map = new HashMap<>();
        api.templateFromYaml("/api/list.yml", map).then().log().all().statusCode(200);
    }
}