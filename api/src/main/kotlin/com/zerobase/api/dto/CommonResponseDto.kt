package com.zerobase.api.dto

data class CommonResponseDto(
    val data: Any,
    val responseCode: String,
    val responseMessage: String
)
