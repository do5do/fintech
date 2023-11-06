package com.zerobase.api.user.dto

import com.zerobase.domain.domain.UserInfo
import io.swagger.annotations.ApiModelProperty

data class UserDto(
    @ApiModelProperty(example = "1e974e1be620476b9245c8a9d6215c41")
    val userKey: String,

    @ApiModelProperty(example = "900101-1234567")
    var userRegistrationNumber: String
) {
    companion object {
        fun fromEntity(userInfo: UserInfo): UserDto {
            return UserDto(userInfo.userKey, userInfo.userRegistrationNumber)
        }
    }
}
