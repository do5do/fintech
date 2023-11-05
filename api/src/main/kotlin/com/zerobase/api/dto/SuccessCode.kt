package com.zerobase.api.dto

enum class SuccessCode(
    val responseCode: String,
    val responseMessage: String
) {
    IS_OK("00", "success")
}