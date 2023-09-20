package com.example.demo.handler;

import cn.hutool.core.util.StrUtil;
import com.example.demo.entity.core.Response;
import com.example.demo.enums.SystemEnum;
import com.example.demo.handler.exceptions.BusinessException;
import com.example.demo.handler.exceptions.CheckException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @param e CheckException
     * @return CheckException 校验异常
     * @throws CheckException
     */
    @ExceptionHandler(value = CheckException.class)
    public Response<String> checkExceptionHandler(CheckException e) {
        log.error(e.getMessage(), e);
        return Response.fail(e.getErrorCode());
    }

    /**
     * @param e BusinessException
     * @return BusinessException 业务异常
     * @throws BusinessException
     */
    @ExceptionHandler(value = BusinessException.class)
    public Response<String> businessExceptionHandler(BusinessException e) {
        log.error(StrUtil.format("{}---{}", e.getMessage(), e.getErrorMsg()), e);
        return Response.fail(e.getErrorCode());
    }

    /**
     * @param e RuntimeException
     * @return RuntimeException 自定义信息
     * @throws RuntimeException
     */
    @ExceptionHandler(value = RuntimeException.class)
    public Response<String> runtimeExceptionHandler(RuntimeException e) {
        log.error(e.getMessage(), e);
        return Response.fail(SystemEnum.SYSTEM_002);
    }

    /**
     * @param e Exception
     * @return Exception 自定义信息
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public Response<String> exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return Response.fail(SystemEnum.SYSTEM_003);
    }
}
