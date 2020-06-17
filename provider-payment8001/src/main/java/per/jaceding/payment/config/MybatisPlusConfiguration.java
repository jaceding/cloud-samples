package per.jaceding.payment.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Mybatis Plus配置类
 * 设置 proxyTargetClass=true 启动CGLIB实现代理
 *
 * @author jaceding
 * @date 2020/6/16
 */
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan("per.jaceding.payment.dao")
public class MybatisPlusConfiguration {

    /**
     * 注入分页插件
     *
     * @return {@link com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor}
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
