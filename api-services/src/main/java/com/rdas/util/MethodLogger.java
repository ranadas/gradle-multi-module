package com.rdas.util;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * https://stackoverflow.com/questions/30335563/spring-boot-logger-aspects
 */
@Aspect
@Component
public class MethodLogger {
//	private long startTime;
//	@AfterReturning("execution(* com.rdas..*.*(..))")
//	public void logMethodAccessAfter(JoinPoint joinPoint) {
//		//System.out.println("***** Completed: " + joinPoint.getSignature().getName() + " *****");
//		long now = System.currentTimeMillis();
//		long elapsedTime = System.nanoTime() - startTime;
//		System.out.println(String.format("Took %d seconds.",
//				(TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS))));
//		System.out.println("***** Completed: " + joinPoint.getSignature().getName() + " *****");
//	}
//
//	@Before("execution(* com.rdas..*.*(..))")
//	public void logMethodAccessBefore(JoinPoint joinPoint) {
//		//System.out.println("***** Starting: " + joinPoint.getSignature().getName() + " *****");
//		System.out.println("***** Starting: " + joinPoint.getSignature().getName() + " *****");
//		startTime = System.currentTimeMillis();
//	}

	@Around("execution(* *(..)) && @annotation(Loggable)")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = point.proceed();
		/*String logString = String.format(
				"#%s(%s): %s in %[msec]s",
				MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
				point.getArgs(),
				result,
				System.currentTimeMillis() - start
		);*/
		String logString = String.format(
				"#method : %s executed, returned : %s in %d milisecs.",
				MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
				result,
				(System.currentTimeMillis() - start)
		);
		System.out.println(logString);
		return result;
	}
}
