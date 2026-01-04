package com.dave.curso.springboot.app.aop.springbootaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1) // para dar orden de ejecución, el orden 1 es el primero en entrar y ultimo en salir
@Aspect
@Component
public class GreetingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Pointcut("execution(* com.dave.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    // private void greetingLoggerPointCut(){}

    //Un * es para cualquier clase, un (..) es para cualquier metodo, cuando se hace .. es cualquier package y subpackage
    @Before("GreetingPointCut.greetingLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Antes: " + method + " con los argumentos " + args);
    }

    @After("GreetingPointCut.greetingLoggerPointCut()")
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Después: " + method + " con los argumentos " + args);
    }

    @AfterThrowing("GreetingPointCut.greetingLoggerPointCut()")
    public void loggerAfterThrowing(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("Después de lanzar la excepción: " + method + " con los argumentos " + args);
    }

    @Around("GreetingPointCut.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            logger.info("El método: " + method + " con los parametros " + args); //before
            result = joinPoint.proceed();
            logger.info("El método: " + method + " retorna el resultado: " + result); //after
            return result;
        } catch (Throwable e) {
            logger.info("Error en la llamada  " + method ); // after throwing
            throw new RuntimeException(e);
        }
    }
}
