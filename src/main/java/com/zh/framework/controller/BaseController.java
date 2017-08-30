package com.zh.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;

public  class BaseController<T> {

    @Autowired
    T service;

}
