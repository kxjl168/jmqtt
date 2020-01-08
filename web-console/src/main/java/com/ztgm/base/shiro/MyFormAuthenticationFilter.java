package com.ztgm.base.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {


    /**
     * 从session中获取验证码所使用的key
     */
    private static String validateCodeKey = "validateCode";

    public static final String shiroLoginFailureKey = "shiroLoginFailure";

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        HttpServletRequest req = (HttpServletRequest)request;
        //当用户访问uri等于登陆uri
        if(this.getLoginUrl().equals(req.getRequestURI()) && "post".equalsIgnoreCase(req.getMethod())){
            HttpSession session = req.getSession();

            //用户填写的验证码
            String userValidateCode = request.getParameter(validateCodeKey);

            //用户为填写验证码不进行身份验证
          /*  if(null==userValidateCode || "".equals(userValidateCode.trim())){
                request.setAttribute(shiroLoginFailureKey, "validateCodeIsNullError");
                return true;
            }*/

            String crctValidateCode = (String) session.getAttribute(validateCodeKey);

            //验证用户填写验证码是否正确
            /*if(userValidateCode!=null && crctValidateCode!=null && !userValidateCode.equals(crctValidateCode)){
                //如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中
                request.setAttribute(shiroLoginFailureKey, "validateCodeIncorrectError");
                //拒绝访问，不再校验账号和密码
                return true;
            }*/
        }

        return super.onAccessDenied(request, response);
    }

    public String getValidateCodeKey() {
        return validateCodeKey;
    }

    public void setValidateCodeKey(String validateCodeKey) {
        this.validateCodeKey = validateCodeKey;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        boolean logined = isAccessAllowed(request, response, mappedValue);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String method = httpServletRequest.getMethod();
        String uri = httpServletRequest.getRequestURI();

        if(logined && "post".equalsIgnoreCase(method) && this.getLoginUrl().equals(uri)){
            return super.executeLogin(request,response);
        }else{
            return super.onPreHandle(request, response, mappedValue);
        }
    }

}
