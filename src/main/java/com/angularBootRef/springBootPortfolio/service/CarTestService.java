package com.angularBootRef.springBootPortfolio.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class CarTestService {


    public String hello() {
        return "hello from api";
    }
}
