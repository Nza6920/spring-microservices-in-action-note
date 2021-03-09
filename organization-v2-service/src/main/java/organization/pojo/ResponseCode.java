package organization.pojo;

/**
 * 响应类型
 *
 * @author nza
 * @createTime 2021/3/3 10:20
 */
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(1, "SUCCESS"),

    /**
     * 错误
     */
    ERROR(-1, "ERROR");


    /**
     * 状态码
     */
    private final int code;

    /**
     * 描述
     */
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
