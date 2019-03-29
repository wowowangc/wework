package com.hogs.wework.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;

class DepartmentTest {

    Department department;

    @BeforeEach
    void setUp() {
        if (department == null) {
            department = new Department();
            //department.deleteAll();
        }
    }

    @Test
    void list() {
        //department.list("1").then().statusCode(200).body("department[0].name",equalTo("TeamWork"));
        department.list("0").then().log().all().body("department[0].id", equalTo(1));
        department.list("").then().log().all().body("department[0].id", equalTo(1));
    }

    @Test
    void create() {

        String nameOld = "王" + department.rand;
        department.create(nameOld, "1").then().body("errcode", equalTo(0));
        //department.create("team1", "1").then().body("errcode",equalTo(60008));
        department.list("1").path("department.find{ it.name == '" + nameOld + "'}.id");
    }

    @Test
    void createWithChinese() {
        department.create("思寒department" + department.rand, "1").then().body("errcode", equalTo(0));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data/csv.csv")
    void createWithDup(String name, Integer expectCode) {
        department.create(name + department.rand, "1").then().body("errcode", equalTo(0));
        department.create(name + department.rand, "1").then().body("errcode", equalTo(expectCode));

    }


    @Test
    void createByMap() {
        String nameOld = "王" + department.rand;
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", nameOld);
        map.put("parentid", "1");

        department.create(map).then().body("errcode", equalTo(0));
        department.create(map).then().body("errcode", equalTo(60008));
        department.list("1").path("department.find{ it.name == '" + nameOld + "'}.id");
    }


    @Test
    void update() {
        department.update("teamx", "1");
    }


    @Test
    void updateAll(){
        //todo:
        HashMap<String, Object> map=new HashMap<>();
        department.readApiFromYaml("readApiFromYaml.json", map).then().statusCode(200);
    }

    @Test
    void deleteAll(){
        department.deleteAll();
    }

    @Test
    void delete() {
        String nameOld = "王" + department.rand;
        department.create(nameOld, "1");
        Integer x = department.list("").path("department.find{ it.name == '" + nameOld + "'}.id");
        System.out.println("x=" + x);
        String id = x.toString();
        department.delete(id).then().body("errcode", equalTo(0));
    }

    @Test
    void delete0() {
        Integer x = 2;
        System.out.println("x=" + x);
        String id = x.toString();
        department.delete(id).then().log().all();
    }


}