package com.jxy.config.dataConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:config/jdbc.properties")
@EnableTransactionManagement//开启事务及对注解@Transactional的支持
@EnableJpaRepositories(basePackages = "com.jxy.repository")//扫描Repository包
@EntityScan(basePackages = "com.jxy.entity")
public class DataConfig {
    //数据库url
    @Value("${spring.datasource.url}")
    private String dbUrl;
    //用户名
    @Value("${spring.datasource.username}")
    private String username;
    //密码
    @Value("${spring.datasource.password}")
    private String password;
    //驱动
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    //初始化链接数量
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    //池中保持的最小连接数
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    //最大连接数
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    //最大等待可以连接时间
    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    //是否对预处理语句（prepared statement）进行池管理
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    //同一时间最大分配的预处理语句数量
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean     //声明其为Bean实例
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }

    //jdbcTemplate配置
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
//
//    //spring容器方式的entity工厂
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
//        LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        managerFactoryBean.setDataSource(dataSource);
//        managerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
//        Properties properties = new Properties();
//        properties.setProperty("dialect", "org.hibernate.dialect.MySQL5Dialect");
//        properties.setProperty("autoCommit ", "true");
//        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("format_sql", "true");
//        managerFactoryBean.setJpaProperties(properties);
//        managerFactoryBean.setPackagesToScan("com.jxy.entity");
//        return managerFactoryBean;
//    }
//    //指定实现厂商专用特性，此处指定mysql
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
//        jpaVendorAdapter.setDatabasePlatform("MYSQL");
//        jpaVendorAdapter.setShowSql(true);
//        jpaVendorAdapter.setGenerateDdl(false);
//        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
//        return jpaVendorAdapter;
//    }
//    // Spring Data JPA事务配置
//   @Bean
//   public JpaTransactionManager transactionManager() {
//       return new JpaTransactionManager(); // does this need an emf???
//   }
}