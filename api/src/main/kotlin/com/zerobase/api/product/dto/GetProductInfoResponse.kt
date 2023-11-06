package com.zerobase.api.product.dto

import io.swagger.annotations.ApiModelProperty

data class GetProductInfoResponse(
    val data: List<ProductDto>,

    @ApiModelProperty(example = "00")
    val responseCode: String,

    @ApiModelProperty(example = "success")
    val responseMessage: String
)
