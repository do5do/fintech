package com.zerobase.api.aop

import com.zerobase.api.exception.CustomException
import com.zerobase.api.exception.ErrorCode
import com.zerobase.api.util.encrypt.EncryptComponent
import com.zerobase.domain.annotation.Encrypt
import mu.KotlinLogging
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import java.lang.Exception
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation

@Aspect
@Component
class EncryptAspect(
    private val encryptComponent: EncryptComponent
) {
    val logger = KotlinLogging.logger {}

    @Pointcut("execution(* com.zerobase.domain..*Repository.save(..))")
    private fun save() {
    }

    @Pointcut("execution(* com.zerobase.domain..*Repository.find*(..))")
    private fun find() {
    }

    @Before("save()")
    private fun encryptEntity(joinPoint: JoinPoint) {
        val args = joinPoint.args[0]

        for (obj in args::class.declaredMemberProperties) {
            if (obj.hasAnnotation<Encrypt>()) {
                val fieldName = obj.name
                val declaredField = args.javaClass.getDeclaredField(fieldName)
                declaredField.trySetAccessible()
                val value = declaredField.get(args)
                declaredField.set(args, encryptComponent.encryptString(value.toString()))
                logger.info { "encrypted $fieldName" }
            }
        }
    }

    @AfterReturning("find()", returning = "result")
    private fun decryptEntity(result: Any) {
        if (!ObjectUtils.isEmpty(result)) {
            for (obj in result::class.declaredMemberProperties) {
                if (obj.hasAnnotation<Encrypt>()) {
                    val fieldName = obj.name
                    val declaredField = result.javaClass.getDeclaredField(fieldName)
                    declaredField.trySetAccessible()
                    val value = declaredField.get(result)
                    declaredField.set(result, encryptComponent.decryptString(value.toString()))
                    logger.info { "decrypted $fieldName" }
                }
            }
        }
    }

    @AfterThrowing("save() || find()", throwing = "exception")
    private fun exception(joinPoint: JoinPoint, exception: Exception) {
        logger.error { "${joinPoint.signature.name} failure. $exception" }
        throw CustomException(ErrorCode.ENCRYPTION_FAILURE)
    }
}