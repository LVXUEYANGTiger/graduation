package yang.org.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author LvXueYang
 * @description 返回结果实体类
 */
@Setter
@Getter
public class Result<T> {
    /**
     * description: 是否成功
     */ 
    private boolean flag;
    /**
     * description: 返回码
     */ 
    private Integer code;
    /**
     * description: 返回消息
     */ 
    private String message;
    /**
     * description: 返回数据
     */ 
    private T data;

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "操作成功!";
    }
}
