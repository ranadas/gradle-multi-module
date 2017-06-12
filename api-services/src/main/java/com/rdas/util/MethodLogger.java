package com.rdas.util;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * http://www.makeinjava.com/logging-aspect-restful-web-service-using-spring-aop-log-requests-responses/
 * https://stackoverflow.com/questions/30335563/spring-boot-logger-aspects
 */
@Aspect
@Component
public class MethodLogger {
    private final Logger logger = LoggerFactory.getLogger(MethodLogger.class);
    /*
	@AfterReturning("execution(* com.rdas..*.*(..))")
	public void logMethodAccessAfter(JoinPoint joinPoint) {
		//System.out.println("***** Completed: " + joinPoint.getSignature().getName() + " *****");
	}
	@Before("execution(* com.rdas..*.*(..))")
	public void logMethodAccessBefore(JoinPoint joinPoint) {
		System.out.println("***** Starting: " + joinPoint.getSignature().getName() + " *****");
	}
	*/

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
        logger.info("#method : {} executed, returned : {} in {} milisecs.",
                MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
                result,
                (TimeUnit.SECONDS.convert((System.currentTimeMillis() - start), TimeUnit.NANOSECONDS))
        );
        return result;
	}
}
