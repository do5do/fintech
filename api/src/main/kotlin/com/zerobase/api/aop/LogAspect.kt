package com.zerobase.api.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class LogAspect {
    val logger = KotlinLogging.logger {}

    @Pointcut("within(com.zerobase.api..*)")
    private fun isApi() {}

    @Around("isApi()")
    fun loggingAspect(joinPoint: ProceedingJoinPoint): Any {
        val stopWath = StopWatch()
        stopWath.start()

        val result = joinPoint.proceed()

        stopWath.stop()

        logger.info { "${joinPoint.signature.name} ${joinPoint.args[0]} ${stopWath.lastTaskTimeMillis}"}

        return result
    }
}