package per.jaceding.payment.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 自定义注解 让ComponentScan跳过该类
 *
 * @author jaceding
 * @date 2020/6/18
 */
@Target(ElementType.TYPE)
public @interface AvoidScan {
}
