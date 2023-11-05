package com.zerobase.api.user

import com.zerobase.domain.domain.UserInfo

class UserRequestDto {
    data class UserRequestInputDto(
        val userName: String,
        val userIncomeAmount: Long,
        var userRegistrationNumber: String
    ) {

        fun toEntity(userKey: String): UserInfo {
            return UserInfo(
                userKey, userRegistrationNumber, userName, userIncomeAmount
            )
        }
    }

    data class UserResponseDto(
        val userKey: String
    )
}