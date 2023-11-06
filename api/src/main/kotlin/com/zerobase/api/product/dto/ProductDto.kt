package com.zerobase.api.product.dto

import com.zerobase.domain.domain.ProductInfo
import io.swagger.annotations.ApiModelProperty

data class ProductDto(
    @ApiModelProperty(example = "001")
    val organizationCode: String,

    @ApiModelProperty(example = "00001")
    val productCode: String,

    @ApiModelProperty(example = "9.9")
    val productMinimumInterest: Double,

    @ApiModelProperty(example = "1.1")
    val productMaximumInterest: Double,

    @ApiModelProperty(example = "상품명")
    val productName: String
) {
    companion object {
        fun fromEntity(productInfo: ProductInfo): ProductDto {
            return ProductDto(
                productInfo.productList.organizationCode.code,
                productInfo.productList.productCode.code,
                productInfo.productMinimumInterest,
                productInfo.productMaximumInterest,
                productInfo.productName
            )
        }
    }
}