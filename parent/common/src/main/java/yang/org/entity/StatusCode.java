package yang.org.entity;

/**
 * @author LvXueYang
 * @description 返回码
 */
public class StatusCode {
    /**
     * description: 成功
     */ 
    public static final int OK = 20000;
    /**
     * description: 失败
     */ 
    public static final int ERROR = 20001;
    /**
     * description: 用户名或密码错误
     */ 
    public static final int LOGINERROR = 20002;
    /**
     * description: 权限不足
     */ 
    public static final int ACCESSERROR = 20003;
    /**
     * description: 远程调用失败
     */ 
    public static final int REMOTEERROR = 20004;
    /**
     * description: 重复操作
     */ 
    public static final int REPERROR = 20005;
    /**
     * description: 没有对应的项目
     */ 
    public static final int NOTFOUNDERROR = 20006;
    /**
     * description: 请求参数错误
     */
    public static final int PARAMETER_ERROR = 20007;
    /**
     * description: 未知的错误
     */
    public static final int UNKNOWN_ERROR = 20008;
}
