package com.example.demo.exception;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.sun.org.apache.bcel.internal.generic.RETURN;


public class R extends Exception {

    String code;
    String message;

    public R(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
