package com.zerobase.api.user

interface UserService {
    fun userRequest(
        userRequestInputDto: UserRequestDto.UserRequestInputDto
    ): UserRequestDto.UserResponseDto

    fun getUser(userKey: String): UserDto
}