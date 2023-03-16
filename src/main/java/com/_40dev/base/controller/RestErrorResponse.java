package com._40dev.base.controller;

import java.util.HashMap;

public class RestErrorResponse extends HashMap<String, String>{


    public static String RT_INFO="information";
    public static String RT_ERROR="error";
    
    RestErrorResponse(String responseType, String messageText) {
        super();
        this.put("responseType", "information");
        this.put("text", messageText);
    }

    public static RestErrorResponse error(String messageText) {
        RestErrorResponse rer=new RestErrorResponse(RestErrorResponse.RT_ERROR,messageText);
        return rer;
    }
    
    public static RestErrorResponse information(String messageText) {
        RestErrorResponse rer=new RestErrorResponse(RestErrorResponse.RT_INFO, messageText);
        return rer;
    }


}
