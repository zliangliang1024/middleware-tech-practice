package liangliang.study.red.envelops.beans;

/**
 * 通用的状态码类
 */
public enum StatusCode {
    SUCCESS(200,"成功"),
    FAIL(-1,"失败"),
    INVALID_PARAM(201,"非法的参数"),
    INVALID_GRANT_TYPE(202,"非法的授权类型");
    // 以下是暂时设定的几种状态码类
    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
