package yang.org.exception;

/**
 * @author LvXueYang
 * @description 自定义异常类,方便我们去请求get,当请求失败时,手动抛出自定义异常,交由全局异常处理
 */
public class ParamsErrorException extends RuntimeException{
    public ParamsErrorException() {
    }

    public ParamsErrorException(String message) {
        super(message);
    }
}
