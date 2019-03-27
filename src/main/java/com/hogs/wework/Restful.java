package com.hogs.wework;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class Restful {
    HashMap<String, Object> query = new HashMap<String, Object>();
    public RequestSpecification requestSpecification = RestAssured.given().log().all();

    public Response send(){
        if (query != null) {
            query.entrySet().forEach(entry -> {
                requestSpecification.queryParam(entry.getKey(), entry.getValue());
            });
        }

        return requestSpecification.when().get("www.baidu.com");
    }


    public static String template(String path, HashMap<String, Object> map){
        DocumentContext documentContext = JsonPath.parse(Restful.class.getResourceAsStream(path));

        map.entrySet().forEach(entry ->{
            documentContext.set(entry.getKey(), entry.getValue());
        });
        return documentContext.jsonString();
    }

}
