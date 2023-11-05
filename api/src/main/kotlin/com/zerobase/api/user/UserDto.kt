package com.zerobase.api.user

import com.zerobase.domain.domain.UserInfo

data class UserDto(
    val userKey: String,
    var userRegistrationNumber: String
) {
    companion object {
        fun fromEntity(userInfo: UserInfo): UserDto {
            return UserDto(userInfo.userKey, userInfo.userRegistrationNumber)
        }
    }
}
