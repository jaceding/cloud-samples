package per.jaceding.payment.vo;


import lombok.*;

/**
 * 统一返回对象
 *
 * @author jaceding
 * @date 2020/6/16
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    /**
     * 请求状态值
     */
    private Integer code;

    /**
     * 接口返回信息
     */
    private String message;

    /**
     * 接口返回数据
     */
    private Object data;

    public Result(Integer code, String message) {
        this(code, message, null);
    }

    /**
     * 封装请求成功DTO
     */
    public static Result success() {
        return new Result(200, "success");
    }

    /**
     * 封装请求成功DTO
     *
     * @param message 返回消息
     */
    public static Result success(String message) {
        return new Result(200, message);
    }

    /**
     * 封装请求成功DTO
     *
     * @param data 返回数据
     */
    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    /**
     * 封装请求失败DTO
     *
     * @param code    失败码
     * @param message 失败消息
     */
    public static Result fail(Integer code, String message) {
        return new Result(code, message);
    }

    /**
     * 封装请求失败DTO
     *
     * @param message 失败消息
     */
    public static Result fail(String message) {
        return new Result(400, message);
    }
}
