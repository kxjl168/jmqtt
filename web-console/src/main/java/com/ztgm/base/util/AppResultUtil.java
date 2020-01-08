package com.ztgm.base.util;

public class AppResultUtil {

    public static final String no_login_code = "00";
    public static final String no_login_message = "no_login";
    public static final String no_user_code = "01";
    public static final String no_user_message = "no this user";

    public static final String success_code = "100";
    public static final String success_message = "success";
    public static final String fail_code = "-100";
    public static final String fail_message = "fail";


    public static AppResult fire(String code,String message,Object obj){
        AppResult rs = new AppResult();
        rs.setCode(code);
        rs.setMessage(message);
        rs.setData(obj);
        return rs;
    }

    public static AppResult success() {
        AppResult rs = new AppResult();
        rs.setCode(success_code);
        rs.setMessage(success_message);
        rs.setData(null);
        return rs;
    }

    public static AppResult success(Object obj) {
        AppResult rs = new AppResult();
        rs.setCode(success_code);
        if(obj.getClass().equals(String.class))
        {
        	rs.setMessage(String.valueOf(obj));
        }
        else
        {
        rs.setMessage(success_message);
        rs.setData(obj);
        }
        return rs;
    }

    public static AppResult fail() {
        AppResult rs = new AppResult();
        rs.setCode(fail_code);
        rs.setMessage(fail_message);
        rs.setData(null);
        return rs;
    }

    public static AppResult fail(String msg) {
        AppResult rs = new AppResult();
        rs.setCode(fail_code);
        rs.setMessage(msg);
        rs.setData(null);
        return rs;

    }
}
