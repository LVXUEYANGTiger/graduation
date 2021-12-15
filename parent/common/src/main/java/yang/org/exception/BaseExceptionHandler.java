package yang.org.exception;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import yang.org.entity.Result;
import yang.org.entity.StatusCode;

import java.util.List;

/**
 * @author LvXueYang
 * @description 公共异常类处理
 */
@ControllerAdvice
public class BaseExceptionHandler {
    /**
     * @description 忽略参数异常处理器
     * @param e: 忽略参数异常
     * @return Result<Exception>
     * @author LvXueYang
     * @date 2021/11/30 10:20
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Result<Exception> parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        return new Result<Exception>(false,StatusCode.PARAMETER_ERROR,"请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * @description 缺少请求体异常处理器
     * @param e: 缺少请求体异常
     * @return Result
     * @author LvXueYang
     * @date 2021/11/30 10:21
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result<Exception> parameterBodyExceptionHandler(HttpMessageNotReadableException e) {
        return new Result<Exception>(false,StatusCode.PARAMETER_ERROR, "参数体不能为空");
    }
    /**
     * @description 参数验证异常处理器
     * @param e:参数验证异常
     * @return Result<Exception>
     * @author LvXueYang
     * @date 2021/11/30 10:24
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<Exception> parameterExceptionHandler(MethodArgumentNotValidException e) {
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return new Result<Exception>(false,StatusCode.PARAMETER_ERROR, fieldError.getDefaultMessage());
            }
        }
        return new Result<Exception>(false,StatusCode.PARAMETER_ERROR,"参数验证出现问题");
    }
    /**
     * @description 自定义参数错误异常处理器
     * @param e:  自定义参数
     * @return Result<Exception>
     * @author LvXueYang
     * @date 2021/11/30 10:28
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ParamsErrorException.class})
    @ResponseBody
    public Result<ParamsErrorException> paramExceptionHandler(ParamsErrorException e) {
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (!StringUtils.isEmpty(e.getMessage())) {
            return new Result<ParamsErrorException>(false,StatusCode.PARAMETER_ERROR, e.getMessage());
        }
        return new Result<ParamsErrorException>(false,StatusCode.PARAMETER_ERROR,"自定义参数异常");
    }

}
