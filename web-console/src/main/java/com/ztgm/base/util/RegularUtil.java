package com.ztgm.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {

    //判断手机号码
    public static Boolean judgePhone(String phone) {
        String pattern = "[1-9]\\d{10}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(phone);
        return !m.matches();
    }

    //判断参数空值
    public static Boolean isNull(Object obj1) {
        return obj1 == null || "".equals(obj1);
    }


}
