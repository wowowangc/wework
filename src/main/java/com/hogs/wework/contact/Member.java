package com.hogs.wework.contact;


import io.restassured.response.Response;

import java.util.HashMap;

public class Member extends Contact {

    public Response create(HashMap<String, Object> map){
        reset();
        String body = template("/data/memberCreate.json", map);
        return requestSpecification
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create")
                .then().log().body().extract().response();

    }
}
