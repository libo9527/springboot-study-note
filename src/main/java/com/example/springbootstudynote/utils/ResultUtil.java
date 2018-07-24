package com.example.springbootstudynote.utils;

import com.example.springbootstudynote.dto.Result;
import com.example.springbootstudynote.enums.ResultEnum;

/**
 * @Description： 请求结果工具类，方便代码复用
 * @Auther： Administrator
 * @date： 2018/7/24:21:39
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result<>(ResultEnum.SUCCESS);
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(Integer code, String message) {
        return new Result(code, message);
    }

    public static Result error(ResultEnum resultEnum) {
        return new Result<>(resultEnum);
    }
}
