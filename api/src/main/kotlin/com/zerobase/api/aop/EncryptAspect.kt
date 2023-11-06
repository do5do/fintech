package com.zerobase.api.aop

import com.zerobase.api.user.dto.UserDto
import com.zerobase.api.user.dto.UserRequestDto
import com.zerobase.api.util.encrypt.EncryptComponent
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class EncryptAspect(
    private val encryptComponent: EncryptComponent
) {
    val logger = KotlinLogging.logger {}

    @Pointcut("execution(* com.zerobase.api..*Service.*Request(..))")
    private fun request() {
    }

    @Pointcut("execution(* com.zerobase.api..*Service.get*(..))")
    private fun response() {
    }

    @Around("request())")
    private fun encrypt(joinPoint: ProceedingJoinPoint): Any? {
        val args = joinPoint.args[0]

        if (args is UserRequestDto.UserRequestInput) {
            args.userRegistrationNumber =
                encryptComponent.encryptString(args.userRegistrationNumber)
            logger.info { "encrypted userRegistrationNumber" }
        }

        return joinPoint.proceed()
    }

    @AfterReturning("response()", returning = "result")
    private fun decrypt(result: Any) {
        if (result is UserDto) {
            result.userRegistrationNumber =
                encryptComponent.decryptString(result.userRegistrationNumber)
            logger.info { "decrypted userRegistrationNumber" }
        }
    }

//    @Before("save()") // 포인트컷이 안잡힌다.. 답이 없는데ㅠㅠ find랑 save에서 잡으라는건 리파지토리 말하는거 아닌가?
//    private fun encryptEntity(joinPoint: JoinPoint) {
//        val args = joinPoint.args[0]
//
//        if (args is UserInfo) {
//            val fields = args.javaClass.declaredFields
//
//            for (field in fields) {
//                if (field.isAnnotationPresent(Encrypt::class.java)) {
//                    val declaredField = args.javaClass.getDeclaredField(field.name)
//                    declaredField.canAccess(true)
//                    val value = declaredField.get(args)
//                    declaredField.set(args, encryptComponent.encryptString(value.toString()))
//                    break;
//                }
//            }
//        }
//    }
}