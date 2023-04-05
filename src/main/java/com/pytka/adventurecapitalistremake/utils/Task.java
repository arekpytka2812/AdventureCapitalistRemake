package com.pytka.adventurecapitalistremake.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Method;

@AllArgsConstructor
@Getter
public class Task {

    private Method method;
    private Object[] args;

}
