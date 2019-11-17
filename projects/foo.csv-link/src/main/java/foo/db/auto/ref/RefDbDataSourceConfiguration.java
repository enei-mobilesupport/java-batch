package foo.sample.db.auto.ref;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySources({ @PropertySource("classpath:application-default.properties"),
    @PropertySource("classpath:application${SPRING_PROFILES_ACTIVE:}.properties") })
@EnableTransactionManagement
@MapperScan(basePackages="foo.sample.db.custom.ref", sqlSessionTemplateRef="idpDbSqlSessionTemplate")
public class RefDbDataSourceConfiguration {

    @Value("${reference.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${reference.batch.schema:IDPDB}")
    private String schema;

    @Bean(name="idpDbDataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setSchema(schema);
        return dataSource;
    }

    @Bean(name="idpDbTransactionManager")
    public DataSourceTransactionManager transactionManager(
            @Qualifier("idpDbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name="idpDbJdbcTemplate")
    public JdbcTemplate jdbcTemplate(
            @Qualifier("idpDbDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="idpDbSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("idpDbDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
    
    @Bean(name = "idpDbSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("idpDbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
