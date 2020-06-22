package per.jaceding.account.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Mybatis Plus配置类
 * 设置 proxyTargetClass=true 启动CGLIB实现代理
 * <p>
 * 配置数据源代理与Seata整合
 *
 * @author jaceding
 * @date 2020/6/16
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan("per.jaceding.account.dao")
public class MybatisPlusConfiguration {

    @Value(value = "${mybatis-plus.mapper-locations}")
    private String mapperLocation;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Primary
    @Bean("dataSource")
    public DataSourceProxy dataSource(DataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();

        // 配置mybatis-plus的分页
        Interceptor[] plugins = {paginationInterceptor()};
        sqlSessionFactoryBean.setPlugins(plugins);

        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocation));

        // 配置spring的本地事务LogicSqlInjector
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());

        // 配置mybatis-plus的log打印
        MybatisConfiguration cfg = new MybatisConfiguration();
        cfg.setJdbcTypeForNull(JdbcType.NULL);
        cfg.setMapUnderscoreToCamelCase(true);
        cfg.setCacheEnabled(false);
        cfg.setLogImpl(StdOutImpl.class);
        sqlSessionFactoryBean.setConfiguration(cfg);

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 注入分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
