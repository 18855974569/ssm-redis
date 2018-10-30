package com.wjx.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AOP切面测试
 * @author wjx
 */
@Aspect
public class HelloWorldAop {
	
	@Pointcut("execution(* com.wjx.aspect.*.*(..))")
    private void aopTest() {

    }
	
	@Before("aopTest()")
    public void before() {
        System.out.println("before*****************");
    }
	
	@After("aopTest()")
	public void after() {
		System.out.println("after********");
	}
}
