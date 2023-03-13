package com.example.pidev1.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect

    public class aspectClass {
        @AfterReturning("execution(* com.example.pidev1.aspect.*.*(..))")
        public void logEntry(JoinPoint jp) {
            String nom =jp.getSignature().getName();
            log.info("bien execution de la méthode" +nom);
        }
}
