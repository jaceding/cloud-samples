package per.jaceding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 主类
 * 给需要监控的服务注入
 *     @Bean
 *     public ServletRegistrationBean getServlet(){
 *         HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
 *         ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
 *         registrationBean.setLoadOnStartup(1);
 *         registrationBean.addUrlMappings("/actuator/hystrix.stream");
 *         registrationBean.setName("HystrixMetricsStreamServlet");
 *         return registrationBean;
 *     }
 *  URL填上：http://localhost:8008/actuator/hystrix.stream（ip和端口需要相应的调整）
 *
 * @author jaceding
 * @date 2020/6/16
 */
@EnableHystrixDashboard
@EnableEurekaClient
@SpringBootApplication
public class HystrixDashboardApp {

    /**
     * 主方法
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApp.class, args);
    }
}
