package com.hogs.wework.contact;

import com.hogs.wework.Api;
import com.hogs.wework.Wework;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Contact extends Api {


    public String rand = String.valueOf(System.currentTimeMillis());

    public Contact() {
        reset();
    }


    public void reset() {
        requestSpecification = given()
                .log().all()
                .queryParam("access_token", Wework.getToken())
                .contentType(ContentType.JSON);


        requestSpecification.filter((req, res, ctx) -> {
            //todo: 对请求 响应做封装
            return ctx.next(req, res);
        });
    }
}
