package com.gairola.gairolaapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before(value = "execution(* com.gairola.gairolaapp.controller.AuthController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.info("Before method:" + joinPoint.getSignature());

    }

    @After(value = "execution(* com.gairola.gairolaapp.controller.AuthController.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        logger.info("After method:" + joinPoint.getSignature());

    }

}
