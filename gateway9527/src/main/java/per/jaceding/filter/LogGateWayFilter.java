package per.jaceding.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 日志打印、id值检查是否合法
 *
 * @author jaceding
 * @date 2020/6/19
 */
@Slf4j
@Component
public class LogGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        StringBuilder logBuf = new StringBuilder();
        ServerHttpRequest request = exchange.getRequest();
        logBuf.append("IP：").append(request.getRemoteAddress()).append("\n")
                .append("URI：").append(request.getURI()).append("\n")
                .append("Id：").append(request.getId()).append("\n")
                .append("Headers：").append(request.getHeaders()).append("\n")
                .append("MethodValue：").append(request.getMethodValue()).append("\n")
                .append("QueryParams：").append(request.getQueryParams()).append("\n")
                .append("Body：").append(request.getBody());
        log.info(logBuf.toString());
        if (request.getQueryParams().containsKey("id")) {
            List<String> idList = request.getQueryParams().get("id");
            for (String idStr : idList) {
                try {
                    int id = Integer.parseInt(idStr);
                    if (id < 0) {
                        throw new IllegalArgumentException("id不能为负数");
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                    return exchange.getResponse().setComplete();
                }
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
