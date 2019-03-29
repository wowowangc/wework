package com.hogs.wework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.reset;

public class Api {
    HashMap<String, Object> query = new HashMap<String, Object>();
    public RequestSpecification requestSpecification = RestAssured.given();

    public Response send() {
        if (query != null) {
            query.entrySet().forEach(entry -> {
                requestSpecification.queryParam(entry.getKey(), entry.getValue());
            });
        }

        return requestSpecification.when().get("www.baidu.com");
    }


    public static String template(String path, HashMap<String, Object> map) {
        DocumentContext documentContext = JsonPath.parse(Api.class.getResourceAsStream(path));

        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });
        return documentContext.jsonString();
    }


    public Response templateFromHar(String path, String pattern, HashMap<String, Object> map) {
        DocumentContext documentContext = JsonPath.parse(Api.class.getResourceAsStream(path));

        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });

        String method = documentContext.read("method");
        String url = documentContext.read("url");
        return requestSpecification.when().request(method, url);
    }

    //todo read from swagger
    public Response templateFromSwagger(String path, String pattern, HashMap<String, Object> map) {
        DocumentContext documentContext = JsonPath.parse(Api.class.getResourceAsStream(path));

        map.entrySet().forEach(entry -> {
            documentContext.set(entry.getKey(), entry.getValue());
        });

        String method = documentContext.read("method");
        String url = documentContext.read("url");
        return requestSpecification.when().request(method, url);
    }

    public Response templateFromYaml(String path, HashMap<String, Object> map) {
        //todo yaml生成定义
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            Restful restful = mapper.readValue(WeworkConfig.class.getResourceAsStream(path), Restful.class);

            //get请求，取map中参数替换query参数
            if (restful.method.toLowerCase().contains("get")){
                map.entrySet().forEach(entry->{
                    restful.query.replace(entry.getKey(), entry.getValue().toString());
                });
            }

            reset();
            restful.query.entrySet().forEach(entry -> {
                this.requestSpecification = this.requestSpecification
                        .queryParam(entry.getKey(), entry.getValue());
            });

            return this.requestSpecification
                    .request(restful.method, restful.url)
                    .then().extract().response();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response api(String path, HashMap<String, Object> map) {
        //todo 动态调用
        return null;
    }

    public Response readApiFromYaml(String path, HashMap<String, Object> map) {

        return null;
    }
}
