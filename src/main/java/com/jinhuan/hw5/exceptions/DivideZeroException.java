package com.jinhuan.hw5.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.PAYMENT_REQUIRED, reason="Can not devide by zero!")
public class DivideZeroException extends Exception {
}
