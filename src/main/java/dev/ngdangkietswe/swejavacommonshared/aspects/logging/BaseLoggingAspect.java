package dev.ngdangkietswe.swejavacommonshared.aspects.logging;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ngdangkietswe
 * @since 12/30/2024
 */

@Aspect
@Log4j2
public abstract class BaseLoggingAspect {

    @Pointcut("within(dev.ngdangkietswe.*.grpc.server..*)")
    protected void apiPointcut() {
    }

    @Around("apiPointcut()")
    protected Object logApi(ProceedingJoinPoint joinPoint) throws Throwable {
        var now = System.currentTimeMillis();
        var result = joinPoint.proceed();
        log.info("API {} executed in {} ms", joinPoint.getSignature().getName(), System.currentTimeMillis() - now);
        return result;
    }
}
