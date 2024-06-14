package Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TeamAspect {

    @Before("execution(* Service.impl.TeamServiceImpl.*(..))")
    public void beforePlayerServiceMethodExecution(JoinPoint joinPoint) {
        System.out.println("Before executing PlayerService method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* Service.impl.TeamServiceImpl.*(..))")
    public void afterPlayerServiceMethodExecution(JoinPoint joinPoint) {
        System.out.println("After executing PlayerService method: " + joinPoint.getSignature().getName());
    }
}
