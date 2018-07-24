package com.example.springbootstudynote.exception.handle;

import com.example.springbootstudynote.dto.Result;
import com.example.springbootstudynote.enums.ResultEnum;
import com.example.springbootstudynote.exception.StudyNoteException;
import com.example.springbootstudynote.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description： 统一异常处理类
 * @Auther： Administrator
 * @date： 2018/7/24:21:31
 */
@ControllerAdvice
public class ExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<String> handle(Exception e) {
        if (e instanceof StudyNoteException){
            StudyNoteException studyNoteException = (StudyNoteException) e;
            return ResultUtil.error(studyNoteException.getCode(), studyNoteException.getMessage());
        }else{
            logger.error("【系统异常】{}", e);  // 记录异常
            return ResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
    }
}