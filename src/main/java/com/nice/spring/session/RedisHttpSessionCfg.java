
package com.nice.spring.session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.util.StringUtils;

/**
 * Redis初始化
 * @author Luo Yong
 * @date 2017-03-12
 */
@Configuration
public class RedisHttpSessionCfg {

	/**
	 * session 在 redis cluster 缓存中最长时间<br/>
	 * 可以不设置，默认值是1800秒
	 */
	@Value("${session.maxSeconds:1800}")
	private Integer maxInactiveIntervalInSeconds;
	/**
	 * redis namespace <br/>
	 * 可以不设置
	 */
	@Value("${session.redisNamespace:}")
	private String redisNamespace;

	/**
	 * 初始化 redisHttpSessionConfiguration注入bean到Spring
	 * @return
	 */
	@Bean
	public RedisHttpSessionConfiguration redisHttpSessionConfiguration() {
		RedisHttpSessionConfiguration redisHttpSessionConfiguration = new RedisHttpSessionConfiguration();
		if (getMaxInactiveIntervalInSeconds() != null) {
			redisHttpSessionConfiguration.setMaxInactiveIntervalInSeconds(getMaxInactiveIntervalInSeconds());
		}
		String namespace = getRedisNamespace();
		if (namespace != null && !StringUtils.isEmpty(namespace.trim())) {
			redisHttpSessionConfiguration.setRedisNamespace(namespace.trim());
		}
		return redisHttpSessionConfiguration;
	}

	public Integer getMaxInactiveIntervalInSeconds() {
		return maxInactiveIntervalInSeconds;
	}

	public String getRedisNamespace() {
		return redisNamespace;
	}
}
