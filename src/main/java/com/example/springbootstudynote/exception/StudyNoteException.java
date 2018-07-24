package com.example.springbootstudynote.exception;

import com.example.springbootstudynote.enums.ResultEnum;

/**
 * @Description： 自定义异常
 * @Auther： Administrator
 * @date： 2018/7/24:21:33
 */
public class StudyNoteException extends RuntimeException{
    /**
     * 错误码
     */
    private Integer code;

    public StudyNoteException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
