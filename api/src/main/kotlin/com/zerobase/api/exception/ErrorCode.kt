package com.zerobase.api.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST

enum class ErrorCode(
    val statusCode: HttpStatus,
    val errorCode: String,
    val errorMessage: String
) {
    RESULT_NOT_FOUND(BAD_REQUEST, "E001", errorMessage = "result not found"),
    PRODUCT_LIST_NOT_FOUND(BAD_REQUEST, "E002", errorMessage = "product list not found"),
    PRODUCT_LIST_ALREADY_EXISTS(BAD_REQUEST, "E003", errorMessage = "product list already exists"),
    USER_NOT_FOUND(BAD_REQUEST, "E004", errorMessage = "user not found"),
    USER_ALREADY_EXISTS(BAD_REQUEST, "E003", errorMessage = "user already exists")
}