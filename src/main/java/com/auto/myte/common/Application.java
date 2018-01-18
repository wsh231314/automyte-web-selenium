package com.auto.myte.common;

import org.springframework.stereotype.Component;

@Component
public class Application {


    //ログインしたユーザ情報のセッションキー
    public static String SESSION_KEY_LOGINUSER = "session_login_key" ;
    

    /** Infoメッセージ設定 **/
    public final static String SERVICE_INFORMATIONS = "informations";
    /** Warningメッセージ設定 **/
    public final static String SERVICE_WARNINGS = "warnings";
    /** Errメッセージ設定 **/
    public final static String SERVICE_ERRORS = "errors";

    /**
     * セッションユーザキー
     */
    public static String DEFAULT_SESSION_AUTH_DATA = "PMI.DEFAULT_SESSION_AUTH_DATA";
    public static String DEFAULT_SESSION_USER_DATA = "PMI.DEFAULT_SESSION_USER_DATA";

    public static final String DEFAULT_FORWARD_URL = "/sessiontimeout";
}
