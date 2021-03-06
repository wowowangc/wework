package com.hogs.wework.contact;

import com.hogs.wework.Wework;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Department extends Contact {

    //V4 read from YAML, send Restful request
    public Response list(String id) {
        reset();
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        Response response = templateFromYaml("/api/list.yml", map);

        //更新为从yaml文件读取
//        Response response = requestSpecification
//                .queryParam("id", id)
//                .when().get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
//                .then().log().body().extract().response();

        return response;
    }

    //V2优化为从json读取数据，使用json-path方式读模板，修改变量 -- 弱模板
    public Response create(String departmentName, String parentId) {
        reset();
        String body = JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/create.json"))
                .set("$.name", departmentName)
                .set("$.parentid", parentId)
                .jsonString();

        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().extract().response();
    }

    //V3添加map传参
    public Response create(HashMap<String, Object> map) {
        reset();
        String body= super.template("/data/create.json", map);
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/create")
                .then().log().body().extract().response();
    }

    public Response delete(String id) {
        reset();
        //V3 Restful Contact
        //return given().log().all().queryParam("access_token", Wework.getToken())
                return requestSpecification
                .queryParam("id", id)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().extract().response();
    }

    //V2优化为从json读取数据，使用json-path方式读模板，修改变量 -- 弱模板
    public Response update(String departmentName, String parentId) {
        reset();
        String body = JsonPath.parse(this.getClass()
                .getResourceAsStream("/data/update.json"))
                .set("$.name", departmentName)
                .set("$.parentid", parentId).jsonString();

        //V3 Restful Contact
        //return given().log().all().queryParam("access_token", Wework.getToken())
        return requestSpecification
                .body(body)
                .when().post("https://qyapi.weixin.qq.com/cgi-bin/department/update")
                .then().extract().response();
    }


    public Response update(HashMap<String, Object> map){
        return templateFromHar(
                "demo.har.json",
                "https://work.weixin.qq.com/wework_admin/party?action=addparty" ,
                map
        );
    }

    public Response deleteAll(){
        reset();
        List<Integer> idList=list("").then().log().all().extract().path("department.id");
        System.out.println(idList);
        idList.forEach(id->delete(id.toString()));
        return null;
    }

    public Response updateAll(HashMap<String, Object> map){
        //return api("api.json", map);
        return readApiFromYaml("readApiFromYaml.json", map);
    }

}
