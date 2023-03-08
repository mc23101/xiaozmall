package com.zhangsisiyao.xiaozmall.product.exception;


import com.zhangsisiyao.common.utils.R;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidException {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public R handleValidException(){
        return R.error();
    }
}
