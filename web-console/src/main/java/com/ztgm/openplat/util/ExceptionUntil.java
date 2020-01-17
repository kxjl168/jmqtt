package com.ztgm.openplat.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

//@ControllerAdvice
public class ExceptionUntil {

    private static Logger logger = LogManager.getLogger(ExceptionUntil.class);

    @ExceptionHandler(value = Exception.class)
    public void handleGlobalException(HttpServletRequest request, Exception ex) {

        //打印堆栈日志到日志文件中
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintWriter(buf, true));
        String expMessage = buf.toString();
        try {
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //记录到日志
        logger.error("GlobalExceptionUntil,捕获异常:" + ex.getMessage() + ";eString:" + expMessage);
    }

    public static String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }

}