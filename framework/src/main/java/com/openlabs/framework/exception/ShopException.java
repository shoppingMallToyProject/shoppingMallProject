package com.openlabs.framework.exception;

public class ShopException extends RuntimeException{

    ShopException() { //기본 생성자
    }

    public ShopException(String message) { // 예외 발생 원인을 전달하기 위한 String 타입의 매개변수를 갖는 생성자
        super(message); //RuntimeException 클래스의 생성자 호출
    }
}
