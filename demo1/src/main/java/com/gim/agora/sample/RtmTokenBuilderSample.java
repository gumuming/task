package com.gim.agora.sample;

import com.gim.agora.rtm.RtmTokenBuilder;

public class RtmTokenBuilderSample {
    private static String appId = "1db10fec940b4219a28c539d2e03eb44";
    private static String appCertificate = "2bca82c964eb44d2b928147998e6641f";
    private static String userId = "10";
    private static int expireTimestamp = 0;

    public static void main(String[] args) throws Exception {
    	RtmTokenBuilder token = new RtmTokenBuilder();
        String result = token.buildToken(appId, appCertificate, userId, RtmTokenBuilder.Role.Rtm_User, expireTimestamp);
        System.out.println(result);
    }
}
