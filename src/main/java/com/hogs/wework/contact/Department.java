package com.hogs.wework.contact;

import com.hogs.wework.Wework;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class Department extends Contact{

    //V1
    public Response list(String id){
        return given().log().all()
                .queryParam("access_token", Wework.getToken())
                .queryParam("id", id)
        .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
        .then().log().all().statusCode(200).extract().response();

    }





    //V1
    public Response create(String departmentName, String parentId){
        return given().log().all().queryParam("access_token", Wework.getToken())
                .body("{\n" +
                        "   \"name\": \""+departmentName+"\",\n" +
                        "   \"parentid\": "+ parentId+",\n" +
                        "   \"order\": 1,\n" +
                        //"   \"id\": 2\n" +
                        "}")
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().all().statusCode(200).extract().response();
    }




    public Response delete(String id){
        reset();
        //V3 Restful Contact
        return given().log().all().queryParam("access_token", Wework.getToken())
        //return requestSpecification
                .queryParam("id",id)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().extract().response();
    }


    //V2优化为从json读取数据，使用json-path方式读模板，修改变量 -- 弱模板
    public Response update(String departmentName, String parentId){
        reset();
        String body = JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/update.json"))
                .set("$.name",departmentName)
                .set("$.parentid",parentId).jsonString();

        //V3 Restful Contact
        //return given().log().all().queryParam("access_token", Wework.getToken())
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().extract().response();
    }

}
