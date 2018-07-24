# 表单验证

> 使用spring-data-jpa的表单验证需要先导入如下依赖
>
> ```
> <dependency>
>     <groupId>org.springframework.boot</groupId>
>     <artifactId>spring-boot-starter-data-jpa</artifactId>
> </dependency>
> ```

![](https://i.loli.net/2018/07/23/5b55d91af0531.jpg)

![](https://i.loli.net/2018/07/23/5b55d93599836.jpg)

![](https://i.loli.net/2018/07/23/5b55dfee0c303.jpg)

![](https://i.loli.net/2018/07/23/5b55e0168bf43.jpg)

# Spring Boot项目中设置Content-Type的方法

**一种比较简单优雅的方法是在注解@RequestMapping中添加produces参数即可**

```java
@RestController
@RequestMapping("/api")
public class ApiController {
    @RequestMapping(value = "/collection", produces="application/json;charset=UTF-8")
    public String home(String op) {
        
    }
```

# 异常

**spring只会对RuntimeException进行异常回滚**所以编写自定义异常时都应该让其继承自Runtime Exception

# 统一异常处理

## 请求结果封装

> 为了统一格式，一般会对请求结果进行一次封装

### 请求结果枚举

```java
package com.example.springbootstudynote.enums;

/**
 * @Description： 请求结果枚举，方便对请求结果的统一维护
 * @Auther： Administrator
 * @date： 2018/7/24:21:47
 */
public enum ResultEnum {

    SUCCESS(0, "成功"),
    UNKONW_ERROR(-1, "未知错误");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
```
### 请求结果封装类
```java
package com.example.springbootstudynote.dto;

import com.example.springbootstudynote.enums.ResultEnum;

/**
 * @Description： 请求结果封装类
 * @Auther： Administrator
 * @date： 2018/7/24:21:34
 */
public class Result<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 请求结果数据
     */
    private T data;

    public Result() {
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public Result(Integer code, String msg) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
```
### 请求结果工具类

```java
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
```

### 自定义异常

```java
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

    public StudyNoteException() {
    }

    public StudyNoteException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public StudyNoteException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
```

### 统一异常处理类

```java
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
```

