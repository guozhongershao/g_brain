package com.musearcher.g_brain.common;

public class Constants {
    /**
    * 服务器地址
    */
    public static final String BASE_URL = "192.168.137.1";
//    public static final String BASE_URL = "192.168.0.";

    /**
     * 端口号
     */
    public static final String PORT = "8080";

    /**
     * 项目名称
     */
    public static final String PROJECT_NAME = "gbrain";

    /**
     * 模块名称
     */
    public static final String MODULE_NAME = "qm/service/ComMobile";

    public static final String TEST_MODULE="test";

    /***************************************** 请求地址 ***********************************/
    public static final String constructUrl(String method){
        return "http://" + BASE_URL + ":" + PORT + "/" +PROJECT_NAME + "/" + TEST_MODULE + "/" + method;
    }
    public static final String GET_BASIC_DATA = constructUrl("GetMobileBasicData.ajax");

    public static final String CONNECT_TEST = constructUrl("login");
}
