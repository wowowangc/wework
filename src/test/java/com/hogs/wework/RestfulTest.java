package com.hogs.wework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestfulTest {

    @Test
    void send() {
        Restful restful = new Restful();
        restful.send().then().log().all().statusCode(200);
    }
}