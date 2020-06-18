package per.jaceding.order.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义的负载均衡策略
 * 每台机器请求指定次数后才轮询到下一台机器
 *
 * @author jaceding
 * @date 2020/6/18
 */
@Slf4j
public class MyRoundRule extends AbstractLoadBalancerRule {

    /**
     * 默认每台机器请求5次
     */
    private static final int DEFAULT_NUM = 5;

    /**
     * 每台机器请求次数
     */
    private int num;

    /**
     * 被调用总次数
     */
    private AtomicInteger count;

    /**
     * 当前机器号
     */
    private int index = 0;

    public MyRoundRule() {
        this(DEFAULT_NUM);
    }

    public MyRoundRule(int num) {
        this.num = num;
        count = new AtomicInteger(0);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }
        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            int nextServerIndex = incrementAndGetModulo(serverCount);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: " + lb);
        }
        return server;
    }

    private int incrementAndGetModulo(int serverCount) {
        for (; ; ) {
            int curCount = count.get();
            int index = (curCount % (serverCount * this.num)) / this.num;
            log.info("index #" + index);
            if (count.compareAndSet(curCount, curCount + 1)) {
                return index;
            }
        }
    }
}
