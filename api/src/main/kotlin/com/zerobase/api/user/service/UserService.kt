package com.zerobase.api.user.service

import com.zerobase.api.user.dto.UserDto
import com.zerobase.api.user.dto.UserRequestDto

interface UserService {
    fun userRequest(
        userRequestInput: UserRequestDto.UserRequestInput
    ): UserRequestDto.UserKeyResponse

    fun getUser(userKey: String): UserDto
}