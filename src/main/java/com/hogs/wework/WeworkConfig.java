package com.hogs.wework;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

public class WeworkConfig {
    //打卡
    public String agentId = "3010011";
    public String secret = "jHSFk3Q-08SsRiqS-v38oCNA0f8493IjLMn_e180Rkg";
    public String corpid = "wwfc0d63ca313ae6d8";
    public String contactSecret = "ktS_fP9wd5uIVwbDYLhl9Dp60T731kQeD-qn0DazZVQ";

    private static WeworkConfig weworkConfig;

    public static WeworkConfig getInstance() {
        if (weworkConfig == null) {
            weworkConfig = load("/conf/WeworkConfig.yml");
            System.out.println(weworkConfig.agentId);
        }
        return weworkConfig;
    }

    public static WeworkConfig load(String path) {
        //TODO read from ymal
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            //System.out.println(mapper.writeValueAsString(WeworkConfig.getInstance()));
            return mapper.readValue(WeworkConfig.class.getResourceAsStream(path), WeworkConfig.class);
         } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
