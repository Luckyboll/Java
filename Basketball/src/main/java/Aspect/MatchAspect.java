package Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MatchAspect {

    @Before("execution(* Service.impl.MatchServiceImpl.*(..))")
    public void beforePlayerServiceMethodExecution(JoinPoint joinPoint) {
        System.out.println("Before executing PlayerService method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* Service.impl.MatchServiceImpl.*(..))")
    public void afterPlayerServiceMethodExecution(JoinPoint joinPoint) {
        System.out.println("After executing PlayerService method: " + joinPoint.getSignature().getName());
    }
}
