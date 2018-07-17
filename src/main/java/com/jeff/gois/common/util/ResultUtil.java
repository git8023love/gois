package com.jeff.gois.common.util;


import com.jeff.gois.common.Result;
import com.jeff.gois.common.enums.ResultEnum;

/**
 * 返回参数工具类
 */
public class ResultUtil {

    /**
     * 成功时返回
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setResult(object);
        result.setMsg("SUCCESS");
        return result;
    }

    /**
     * 成功时返回
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 失败时返回
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }

}
