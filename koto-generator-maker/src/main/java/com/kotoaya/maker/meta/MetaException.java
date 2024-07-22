package com.kotoaya.maker.meta;

/**
 * @author kotoaya
 * 元信息异常类
 */
public class MetaException extends RuntimeException{
    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}