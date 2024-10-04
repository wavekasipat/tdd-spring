package com.example.day1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger LOGGER = LogManager.getLogger(
    LoggingAspect.class
  );

  @Around("execution(* com.example.day1..*(..)))")
  public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint)
    throws Throwable {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();

    final StopWatch stopWatch = new StopWatch();

    // Measure method execution time
    stopWatch.start();
    Object result = proceedingJoinPoint.proceed();
    stopWatch.stop();

    //Log method execution time
    LOGGER.info(
      "Execution time of " +
      className +
      "." +
      methodName +
      " :: " +
      stopWatch.getTotalTimeMillis() +
      " ms"
    );

    return result;
  }

  @Pointcut("execution(* com.example.day1..*(..)))")
  public void logPointcut() {}

  @Before("logPointcut()")
  public void logAllMethodCallsAdvice(JoinPoint joinPoint) {
    System.out.println(
      "In Before Aspect at " + joinPoint.getSignature().getName()
    );
  }

  @After("logPointcut()")
  public void logMethodCallsAfterAdvice(JoinPoint joinPoint) {
    System.out.println(
      "In After Aspect at " + joinPoint.getSignature().getName()
    );
  }
}
