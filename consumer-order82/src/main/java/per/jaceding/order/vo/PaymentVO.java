package per.jaceding.order.vo;

import lombok.*;

import java.io.Serializable;

/**
 * 支付信息VO
 *
 * @author jaceding
 * @date 2020/6/18
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentVO implements Serializable {

    private static final long serialVersionUID = 8885606405835295628L;

    /**
     * 订单流水号
     */
    private String serial;
}
