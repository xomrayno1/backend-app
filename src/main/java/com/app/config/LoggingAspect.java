package com.app.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	
	@Before("execution(* com.app.controller.*.*(..)) || execution(* com.app.service.*.*(..))")
    public void before(JoinPoint joinPoint){
		log.info(" before called method {} " + joinPoint.getSignature());
	}
	
	@After("execution(* com.app.controller.*.*(..)) || execution(* com.app.service.*.*(..))")
    public void after(JoinPoint joinPoint){
        log.info(" after called method {} " + joinPoint.getSignature());
	}
	
	@AfterThrowing(pointcut = "execution(* com.app.controller.*.*(..))", throwing = "error")
	public void afterThrowingAdvice(JoinPoint joinPoint, Throwable error) {
	   log.error("error method  {}  ", joinPoint.getSignature(), error);
	}
	
//	@AfterReturning(pointcut = "execution(* com.app.controller.*.*(..))", returning = "retVal")
//	public void afterReturningAdvice(JoinPoint jp, Object retVal){
//	   System.out.println("Method Signature: "  + jp.getSignature());  
//	   System.out.println("Returning:" + retVal.toString() );
//	}


}
