package com.example.projectDemo.Controllers;

import com.example.projectDemo.Entity.ErrorResponse;
import com.example.projectDemo.Exception.NoSuchProductExistsException;
import com.example.projectDemo.Exception.ProductAlreadyExistsException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NoSuchProductExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleException( NoSuchProductExistsException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }


    @ExceptionHandler(value = ProductAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

//    @ExceptionHandler(IndexOutOfBoundsException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorResponse todoException(Exception ex, WebRequest request) {
//        return new ErrorResponse(10100, "Đối tượng không tồn tại");
//    }
}