package com.ztgm.base.config;

import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ztgm.base.service.PermissionService;
import com.ztgm.base.shiro.CustomCredentialsMatcher;
import com.ztgm.base.shiro.MyFormAuthenticationFilter;
import com.ztgm.base.shiro.MyShiroRealm;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Shiro 配置
 */
@Configuration
public class ShiroConfig {

    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCacheManager(cacheManager);

        //自定义密码比较
        CustomCredentialsMatcher customMathcher=new CustomCredentialsMatcher();
        realm.setCredentialsMatcher(customMathcher);
        
        /*SimpleCredentialsMatcher credentialsMatcher = new SimpleCredentialsMatcher();
        realm.setCredentialsMatcher(credentialsMatcher);*/

        return realm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(myShiroRealm);
//      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
        dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }


    /**
     * ShiroFilter<br/>
     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     *
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, PermissionService permissionService) {
        //
        ShiroFilterFactoryBean shiroFilterFactoryBean = new org.apache.shiro.spring.web.ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.action");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/manager/admin/index.action");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.action");

        Map<String, Filter> filters = new HashMap<>();
        MyFormAuthenticationFilter formAuthenticationFilter = new MyFormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("telephone");//页面userName 输入域名称
        formAuthenticationFilter.setPasswordParam("password");//页面password 输入域名称
        formAuthenticationFilter.setValidateCodeKey("captchaCode");//页面验证码 输入域名称

        filters.put("authc", formAuthenticationFilter);

        //logoutFilter 设置RedirectUrl ，否则再次登录成功时会自动跳转到 项目根目录/
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login.action");
        filters.put("logout", logoutFilter);

        shiroFilterFactoryBean.setFilters(filters);

        //必须用能够保证顺序的map，否则不能保证拦截的顺序
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/lock/**", "anon");
        filterChainDefinitionMap.put("/vendor/**", "anon");
        filterChainDefinitionMap.put("/longin.action", "anon");
        filterChainDefinitionMap.put("/interface/**", "anon");
        
        filterChainDefinitionMap.put("/**/**/interface/**", "anon");
        
        filterChainDefinitionMap.put("/ologin/*", "anon");
        filterChainDefinitionMap.put("/validateCode.action", "anon");

        filterChainDefinitionMap.put("/logout.action", "logout");
        
        
        //文件服务器相关
        filterChainDefinitionMap.put("/FileSvr/**", "anon");
        filterChainDefinitionMap.put("/upload/**", "anon");
        filterChainDefinitionMap.put("/file/**", "anon");
        

        //读取数据库中配置已实现功能
        List<Map> permissions = permissionService.selectPermissions();
        if (null != permissions) {
            for (Map p : permissions) {

            	if(p.get("url")!=null&&!p.get("url").equals(""))
                filterChainDefinitionMap.put((String) p.get("url"), "perms[" + p.get("percode") + "]");
            }
        }

        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}