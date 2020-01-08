package com.ztgm.base.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ztgm.base.pojo.Manager;
import com.ztgm.base.pojo.MenuPermission;
import com.ztgm.base.service.ManagerService;
import com.ztgm.base.service.PermissionService;
import com.ztgm.base.service.RoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * MyShiroRealm
 *
 * @create 2016年1月13日
 */
public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private ManagerService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    /**
     * 权限类型
     * 1：菜单
     * 2：具体功能连接
     */
    public static final String permissionType_1 = "1";
    public static final String permissionType_2 = "2";

    public static Map users = new HashMap();
    public static Map roles = new HashMap();
    public static Map perms = new HashMap();


    /**
     * 权限认证，为当前登录的Subject授予角色和权限
     *
     * @see ：本例中该方法的调用时机为需授权资源被访问时
     * @see ：并且每次访问需授权资源时都会执行该方法中的逻辑，这表明本例中默认并未启用AuthorizationCache
     * @see ：如果连续访问同一个URL（比如刷新），该方法不会被重复调用，Shiro有一个时间间隔（也就是cache时间，在ehcache-shiro.xml中配置），超过这个时间间隔再刷新页面，该方法会被执行
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
        Map principal = (Map) principalCollection.getPrimaryPrincipal();

  
        
        List<MenuPermission> userPermissions = (List<MenuPermission>) principal.get("userPermissions");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (null != userPermissions) {
            for (MenuPermission up : userPermissions) {
                String pStr = (String) up.getPercode();
                if (pStr != null && !pStr.equals("null"))
                    info.addStringPermission(pStr);
            }
        }
        
        

        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String logininput = token.getUsername();

        Manager query=new Manager();
        query.setKey(logininput);

        Manager user = userService.getLoginUser(query);
        //未找到用户信息
        if (null == user) {
            logger.info("登陆未找到用户信息，用户输入：" + logininput);
            return null;
        }


    
        Map principal = new HashMap();
        List<MenuPermission> allUserPermissions = new ArrayList<>();
        List<MenuPermission> userPermissions;

        List<MenuPermission> userMenus = new ArrayList<>();

        // 查询用户角色和权限
        List<Map> roles = roleService.selectRoleByManagerId(user.getId());

        List<MenuPermission> rolePermissions = permissionService.selectPermissionsByManagerId(user.getId());
        if (null != roles) {

            if (null != rolePermissions) {
                for (MenuPermission rr : rolePermissions) {

                    if (!allUserPermissions.contains(rr))
                        allUserPermissions.add(rr);
                }

                for (MenuPermission rr : rolePermissions) {

                    if (permissionType_1.equals(rr.getType())) {
                        userPermissions = new ArrayList();
                        String menuid = String.valueOf(rr.getId());
                        for (MenuPermission r2 : rolePermissions) {
                            String pid = String.valueOf(r2.getParentid());
                            if (pid.equals(menuid))
                                userPermissions.add(r2);
                        }

                        rr.setPermissions(userPermissions);

                        userMenus.add(rr);

                    }

                }


            }
        }

        principal.put("user", user);
        principal.put("userId", user.getId());
        principal.put("userName", user.getNickname() == null || "".equals(user.getNickname()) ? user.getTelephone() : user.getNickname());
        principal.put("userMenus", userMenus);
        principal.put("userPermissions", allUserPermissions);
        principal.put("roles", roles);


        return new SimpleAuthenticationInfo(principal, user.getPassword(), getName());
    }


}