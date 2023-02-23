package com.example.hongpark.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
	
	//특정 어노테이션을 대상으로 지정
	@Pointcut("@annotation(com.example.hongpark.annotation.RunningTime)")
	private void enableRunningTime() {}
	
	//기본 패키지 모든 메소드
	@Pointcut("execution(* com.example.hongpark..*.*(..))")
	private void cut() {}
	
	@Around("cut() && enableRunningTime()")
	public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
		//메소드 수행 전 측정 시작
		StopWatch sw = new StopWatch();
		sw.start();
		
		//메소드 수행
		Object returningObj = joinPoint.proceed();
		
		//메소드 수행 후 측정 종료 및 로깅		
		sw.stop();
		String methodName = joinPoint.getSignature().getName();
		log.info("{}의 총 수행시간 => {}", methodName, sw.getTotalTimeSeconds());
	}
}
