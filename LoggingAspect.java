package com.jaewon.board.asspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.jaewon.board.service.*.get*(..))")
    public void loggerBefore(){
        System.out.println("get 으로 시작하는 메서드 시작 --");

    }

    @After("execution(* com.jaewon.board.service.*.get*(..))")
    public void loggerAfter(){
        System.out.println("get 으로 시작하는 메서드 끝 --");

    }

    @Around("execution(* com.jaewon.board.controller.UserController.*(..))")
    public Object loggerAround(ProceedingJoinPoint pjp)throws Throwable{
        long beforeTimeMillis = System.currentTimeMillis();
        System.out.println("[UserController] 실행 시작 : "
        +pjp.getSignature().getDeclaringTypeName() + "."
        +pjp.getSignature().getName());

        Object result = pjp.proceed();

        long afterTimeMillis = System.currentTimeMillis();
        System.out.println("[UserController] 실행 완료 : " + afterTimeMillis + "밀리초 소요"
                +pjp.getSignature().getDeclaringTypeName() + "."
                +pjp.getSignature().getName());

        return result;

    }
}
