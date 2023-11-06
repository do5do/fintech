package com.zerobase.api.user.dto

import com.zerobase.domain.domain.UserInfo
import io.swagger.annotations.ApiModelProperty

class UserRequestDto {
    data class UserRequestInput(
        @ApiModelProperty(example = "김코드")
        val userName: String,

        @ApiModelProperty(example = "100000")
        val userIncomeAmount: Long,

        @ApiModelProperty(example = "900101-1234567")
        var userRegistrationNumber: String
    ) {

        fun toEntity(userKey: String): UserInfo {
            return UserInfo(
                userKey, userRegistrationNumber, userName, userIncomeAmount
            )
        }
    }

    data class UserKeyResponse(
        @ApiModelProperty(example = "1e974e1be620476b9245c8a9d6215c41")
        val userKey: String
    )

    data class UserResponse(
        val data: UserKeyResponse,

        @ApiModelProperty(example = "00")
        val responseCode: String,

        @ApiModelProperty(example = "success")
        val responseMessage: String
    )
}