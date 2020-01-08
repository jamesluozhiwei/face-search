package com.lzw.face.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * druid数据库连接池配置
 * @author jamesluozhiwei
 * @date 2019/12/23
 */
@Configuration
@PropertySource(value = "classpath:application.yml")
public class DruidConfiguration {

    /**
     * 数据源配置
     * @return 返回连接池
     */
    @Bean(initMethod = "init",destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    /**
     * sql session factory    !!! 使用mybatis-plus 自动配置
     * @param dataSource
     * @return
     * @throws Exception
     */
    //@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean  =  new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath*:/mapper/*/*.xml"));
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }


    /**
     * druid
     * 注册一个StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> druidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //登录查看信息的账号密码.*/
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    /**
     * druid过滤器
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> druidStatFilter(){
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
