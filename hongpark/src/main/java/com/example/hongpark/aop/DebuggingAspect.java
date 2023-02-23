package com.example.hongpark.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect // 부가기능을 주입하는 클래스
@Component
@Slf4j
public class DebuggingAspect {
	
	// 어따 찔러넣을 것인가
	@Pointcut("execution(* com.example.hongpark.api.*.*(..))") 
	private void cut() {
		
	}
	
	//실행 이전에 호출
	@Before("cut()")
	public void loggingArgs(JoinPoint joinPoint) { //cut()의 대상
		//입력값 가져오기
		Object[] args = joinPoint.getArgs();
		
		//클래스명
		String className = joinPoint.getTarget().getClass().getSimpleName();

		//메소드명
		String methodName = joinPoint.getSignature().getName();

		//입력값 로깅
		for(Object obj : args) {
			log.info("{}#{}의 입력값 => {}", className, methodName, obj);
		}
	}
	
	//정상 실행 후 호출
	@AfterReturning(value = "cut()", returning = "returnObj")
	public void loggingReturnValue(JoinPoint joinPoint, Object returnObj) {		
		
		//클래스명
		String className = joinPoint.getTarget().getClass().getSimpleName();

		//메소드명
		String methodName = joinPoint.getSignature().getName();

		//리턴값 로깅
		log.info("{}#{}의 리턴값 => {}", className, methodName, returnObj);

	}
}
