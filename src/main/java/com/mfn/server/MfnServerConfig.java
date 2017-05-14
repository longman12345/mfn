package com.mfn.server;

import com.alibaba.druid.pool.DruidDataSource;
import com.mfn.common.MfnInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

import javax.sql.DataSource;

/**
 * Created by LPF on 2017/2/25.
 */
@SpringBootConfiguration
public class MfnServerConfig extends WebMvcConfigurerAdapter {
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.password}")
    private String pw;
    @Value("${jdbc.driverClassName}")
    private String driver;
    @Value("${jdbc.initialSize}")
    private int initialSize;
    @Value("${jdbc.minIdle}")
    private int minIdle;
    @Value("${jdbc.maxWait}")
    private int maxWait;
    @Value("${jdbc.maxActive}")
    private int maxActive;

    private static final Logger LOG = LoggerFactory.getLogger(MfnServerConfig.class);

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(pw);
        dataSource.setDriverClassName(driver);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setMaxActive(maxActive);
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(getDataSource());
        try {
            bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            LOG.error("sqlSessionFactory has error", e);
        }
        return null;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MfnInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
