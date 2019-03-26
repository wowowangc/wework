package com.hogs.wework;

public class WeworkConfig {
    //打卡
    public String agentId="3010011";
    public String secret="jHSFk3Q-08SsRiqS-v38oCNA0f8493IjLMn_e180Rkg";
    public String corpid="wwfc0d63ca313ae6d8";
    public String contactSecret="ktS_fP9wd5uIVwbDYLhl9Dp60T731kQeD-qn0DazZVQ";

    private static WeworkConfig weworkConfig;
    public static WeworkConfig getInstance(){
        if(weworkConfig == null){
            weworkConfig = new WeworkConfig();
        }
        return weworkConfig;
    }

    public static void load(String path){
        //TODO read from ymal
    }
}
