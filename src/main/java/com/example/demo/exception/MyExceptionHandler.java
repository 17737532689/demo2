package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(R.class)
    public Map<String, Object> handleException(Exception e) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", "notexist");
        map.put("message", e.getMessage());
        return map;

    }
}
