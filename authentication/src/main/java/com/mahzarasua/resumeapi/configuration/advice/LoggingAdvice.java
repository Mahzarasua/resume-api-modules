package com.mahzarasua.resumeapi.configuration.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static com.mahzarasua.resumeapi.configuration.util.DataServiceUtil.OBJECT_MAPPER;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    /*@Pointcut(value = "execution(* com.mahzarasua.resumeapi.controller.*.*(..) ) || " +
            "execution(* com.mahzarasua.resumeapi.service.*.*(..) ) ||" +
            "execution(* com.mahzarasua.resumeapi.repository.*.*(..) )"
    )*/
    @Pointcut(value = "execution(* com.mahzarasua.resumeapi.*.*.*(..) ) && !execution(* com.mahzarasua.resumeapi.config.*.*(..) ) "
    )
    public void myPointcut(){

    }

    @Around("myPointcut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString().replace("class ", "");
        Object[] array = pjp.getArgs();

        log.info("Entering " + className + ":" + methodName + "()" + " arguments: " + OBJECT_MAPPER.writeValueAsString(array));

        Object object = pjp.proceed();

        log.info("Output " + className + ":" + methodName + "()" + " Response: " + OBJECT_MAPPER.writeValueAsString(object));

        return object;
    }
}
