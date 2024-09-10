package com.ricardo.oliveira.padelHubAPI.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.ricardo.oliveira.padelHubAPI.controller.*.*(..))")
    private void pointCutControllerPackage() {}

    @Pointcut("execution(* com.ricardo.oliveira.padelHubAPI.service.*.*(..))")
    private void pointCutServicePackage() {}

    @Pointcut("execution(* com.ricardo.oliveira.padelHubAPI.repository.*.*(..))")
    private void pointCutRepositoryPackage() {}

    @Pointcut("pointCutControllerPackage() || pointCutServicePackage() || pointCutRepositoryPackage()")
    private void pointCutAppFlow() {}

    @Before("pointCutAppFlow()")
    public void before(JoinPoint joinPoint) {
        logger.info("Executing: " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "pointCutAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Result in " + joinPoint.getSignature().toShortString() + " : " + result);
    }
}
