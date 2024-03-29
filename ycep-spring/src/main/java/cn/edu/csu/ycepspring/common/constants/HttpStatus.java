package cn.edu.csu.ycepspring.common.constants;

public class HttpStatus {
    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;

    /**
     * 未登录
     */
    public static final int UNAUTHORIZED = 401;

    /**
     * 无权限
     */
    public static final int FORBIDDEN = 403;

    /**
     * 系统内部错误
     */
    public static final int ERROR = 500;

    /**
     * 业务异常
     */
    public static final int EXCEPTION = 501;
}
