package com.zerobase.api.exception

import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

class ErrorResponse(
    private val customException: CustomException
) {
    fun toResponseEntity(): ResponseEntity<ErrorResponseDto> {
        return ResponseEntity
            .status(customException.errorCode.statusCode)
            .body(
                ErrorResponseDto(
                    errorCode = customException.errorCode.errorCode,
                    errorMessage = customException.errorCode.errorMessage
                )
            )
    }

    data class ErrorResponseDto(
        val errorCode: String,
        val errorMessage: String
    ) {
        val timeStamp = LocalDateTime.now()
    }
}