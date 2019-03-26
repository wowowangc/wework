package com.hogs.wework.contact;

import com.hogs.wework.Restful;
import com.hogs.wework.Wework;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Contact extends Restful {

    public Contact(){
        reset();
    }

    public void reset(){
        requestSpecification.given()
                .log().all()
                .contentType(ContentType.JSON)
                .queryParam("access_token",Wework.getToken());
    }
}
