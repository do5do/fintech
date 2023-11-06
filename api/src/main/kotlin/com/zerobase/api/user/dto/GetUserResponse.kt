package com.zerobase.api.user.dto

import io.swagger.annotations.ApiModelProperty

data class GetUserResponse(
    @ApiModelProperty(position = 2)
    val data: UserDto,

    @ApiModelProperty(example = "00")
    val responseCode: String,

    @ApiModelProperty(example = "success")
    val responseMessage: String
)
