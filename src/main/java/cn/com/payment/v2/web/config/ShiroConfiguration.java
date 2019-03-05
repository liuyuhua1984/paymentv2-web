package cn.com.payment.v2.web.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

import cn.com.payment.v2.web.controller.context.CustomerUserRealm;

/**
 * Shiro 配置
 *
 * @author 单红宇(365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016年1月13日
 */
@Configuration
@Component
public class ShiroConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

	/**
	 * ShiroFilter<br/>
	 * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
	 * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
	 *
	 * @param myShiroRealm
	 * @param stuService
	 * @param scoreDao
	 * @return
	 * @author SHANHY
	 * @create 2016年1月14日
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new MShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/index.html");
		// 登录成功后要跳转的连接
		// shiroFilterFactoryBean.setSuccessUrl("login");
		// 登录失败后的反馈
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		loadShiroFilterChain(shiroFilterFactoryBean);
		return shiroFilterFactoryBean;
	}

	/**
	 * 加载shiroFilter权限控制规则（从数据库读取然后配置）
	 *
	 * @author SHANHY
	 * @create 2016年1月14日
	 */
	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
		/////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
//		filterChainDefinitionMap.put("/login", "authc");// 这里为了测试，只限制/user，实际开发中请修改为具体拦截的请求规则
		// anon：它对应的过滤器里面是空的,什么都没做
		logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
		// filterChainDefinitionMap.put("/user/edit/**",
		// "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取
		// filterChainDefinitionMap.put("/console/**", "anon");// anon
		filterChainDefinitionMap.put("/druid/**", "anon");// anon
		filterChainDefinitionMap.put("/static/**", "anon");// anon
		filterChainDefinitionMap.put("/login", "anon");// anon
		filterChainDefinitionMap.put("/index.html", "anon");
		filterChainDefinitionMap.put("/logout", "logout");// anon 可以理解为不拦截
		filterChainDefinitionMap.put("/**", "authc");// authc 需要拦截
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}

	/**
	 * 配置shiro redisManager
	 * <p>
	 * 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
//    public RedisManager redisManager() {
//        RedisManager redisManager = new RedisManager();
//        redisManager.setHost(host);
//        redisManager.setPort(port);
//        redisManager.setExpire(1800);// 配置缓存过期时间
//        redisManager.setTimeout(timeout);
//        redisManager.setPassword(password);
//        return redisManager;
//    }
	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache.xml");
		return em;
	}

	@Bean(name = "customerUserRealm")
	public CustomerUserRealm myShiroRealm(EhCacheManager cacheManager) {
		CustomerUserRealm realm = new CustomerUserRealm();
		realm.setCacheManager(cacheManager);
		return realm;
	}

	@Bean
	public SessionManager sessionManager() {
//		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		MySessionManager sessionManager = new MySessionManager();
		Collection<SessionListener> listeners = new ArrayList<SessionListener>();
		listeners.add(new MySessionListener());
		sessionManager.setSessionListeners(listeners);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setCacheManager(getEhCacheManager());
		sessionManager.setSessionDAO(getEnterpriseCacheSessionDAO());
		sessionManager.setSessionIdCookie(rememberMeCookie());
		return sessionManager;
	}

	@Bean
	public EnterpriseCacheSessionDAO getEnterpriseCacheSessionDAO() {
		EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
		enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
		enterpriseCacheSessionDAO.setSessionIdGenerator(getJavaUuidSessionIdGenerator());
		return enterpriseCacheSessionDAO;
	}

	/**
	 * cookie对象;
	 * 
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("uid");
		// <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
		simpleCookie.setMaxAge(-1);
		simpleCookie.setPath("/");
		simpleCookie.setHttpOnly(true);
		return simpleCookie;
	}

	@Bean
	public JavaUuidSessionIdGenerator getJavaUuidSessionIdGenerator() {
		JavaUuidSessionIdGenerator javaUuidSessionIdGenerator = new JavaUuidSessionIdGenerator();
		return javaUuidSessionIdGenerator;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(CustomerUserRealm customerUserRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(customerUserRealm);
		dwsm.setSessionManager(sessionManager());
		// <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		dwsm.setCacheManager(getEhCacheManager());
		return dwsm;
	}

	/**
	 * 注册DelegatingFilterProxy（Shiro） 集成Shiro有2种方法： 1.
	 * 按这个方法自己组装一个FilterRegistrationBean（这种方法更为灵活，可以自己定义UrlPattern，
	 * 在项目使用中你可能会因为一些很但疼的问题最后采用它， 想使用它你可能需要看官网或者已经很了解Shiro的处理原理了） 2.
	 * 直接使用ShiroFilterFactoryBean（这种方法比较简单，其内部对ShiroFilter做了组装工作，无法自己定义UrlPattern，
	 * 默认拦截 /*）
	 *
	 * @param dispatcherServlet
	 * @return
	 * @author SHANHY
	 * @create 2016年1月13日
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
		// 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");//
		// 可以自己灵活的定义很多，避免一些根本不需要被Shiro处理的请求被包含进来
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("serverSessionTimeout", "60");
		initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
		filterRegistration.setInitParameters(initParameters);
		return filterRegistration;
	}

	@Bean(name = "formAuthenticationFilter")
	public ShiroFormAuthenticationFilter getFormAuthenticationFilter() {
		return new ShiroFormAuthenticationFilter() ;
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

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}

//	@EventListener
//	public void handleContextRefresh(ContextRefreshedEvent event) {
//		ApplicationContext context = event.getApplicationContext();
//		DefaultWebSecurityManager manager = (DefaultWebSecurityManager) context.getBean("securityManager");
//		AuthorizingRealm realm = (AuthorizingRealm) context.getBean("customerUserRealm");
//		// realm.setCredentialsMatcher(new CustomCredentialsMatcher());
//		manager.setRealm(realm);
//	}
}
