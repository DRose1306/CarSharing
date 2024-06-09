package com.example.carsharing.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * Aspect for logging the execution of controller and service methods.
 */
@Aspect
@Component
@Slf4j //Slf4j is a logging facade for Java
public class LoggingAspect {

    /**
     * Pointcut for logging controller methods.
     */
    @Pointcut("execution(public * com.example.carsharing.controller.*.*(..))")
    public void controllerLog() {
    }

    /**
     * Pointcut for logging service methods.
     */
    @Pointcut("execution(public * com.example.carsharing.service.*.*(..))")
    public void serviceLog() {
    }

    /**
     * Advice for logging before executing controller methods.
     */
    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("""
                        NEW REQUEST:
                        IP : {}
                        URL : {}
                        HTTP_METHOD : {}
                        CONTROLLER_METHOD : {}.{}""",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    /**
     * Advice for logging before executing service methods.
     */
    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    /**
     * Advice for logging after successfully returning from controller methods.
     */
    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("""

                        Return value: {}
                        END OF REQUEST""",
                returnObject);
    }

    /**
     * Advice for logging exceptions thrown by controller methods.
     */
    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(jp.getArgs()), ex.getMessage());
    }
}
