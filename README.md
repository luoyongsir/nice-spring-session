
## 分布式web工程用redis-cluster共享session

使用方法如下：

<br/>
1.添加配置文件

#redis 可选配置<br/>
redis.minIdle=10<br/>
redis.maxIdle=50<br/>
redis.maxTotal=100<br/>
redis.maxWaitMillis=3000<br/>
redis.testOnBorrow=true<br/>
redis.maxRedirects=3<br/>
redis.password=password<br/>

#redis 必填配置<br/>
redis.nodes=ip:port1,ip:port2,ip:port3,ip:port4,ip:port5,ip:port6<br/>

<br/>
下面两个是可选的配置

#session 存活时间，默认是30分钟<br/>
session.maxSeconds=1800<br/>
#session 存储在redis中的命名空间<br/>
session.redisNamespace=a``:``b``:``c<br/>

<br/>
2.pom.xml 添加依赖

    <dependency>
        <groupId>com.nice</groupId>
        <artifactId>nice-spring-session</artifactId>
        <version>1.0.1-RELEASE</version>
    </dependency>

<br/>
3.如果是spring 项目，web.xml 中配置过滤器

    <filter>
        <filter-name>springSessionRepositoryFilter</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>springSessionRepositoryFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

如果是spring boot项目，没有web.xml

    @Bean
	public FilterRegistrationBean springSessionRepositoryFilter() {
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(delegatingFilterProxy);
        bean.addUrlPatterns("/*");
        bean.setDispatcherTypes(DispatcherType.REQUEST);
        bean.setDispatcherTypes(DispatcherType.ERROR);
        return bean;
	}

	配置文件添加
	spring.session.store-type=redis
