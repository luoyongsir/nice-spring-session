
## 分布式web工程利用 spring-session 共享 session 到 redis cluster

使用方法如下：

pom.xml 添加依赖

    <dependency>
        <groupId>com.nice</groupId>
        <artifactId>nice-spring-session</artifactId>
        <version>1.0.0-RELEASE</version>
    </dependency>


web.xml 中配置过滤器

    <filter>
        <filter-name>springSessionRepositoryFilter</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>springSessionRepositoryFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

<br/><br/>
下面两个是可选的配置

#session 存活时间，默认是30分钟<br/>
session.maxSeconds=1800<br/>
#session 存储在redis中的命名空间<br/>
session.redisNamespace=a``:``b``:``c<br/>
