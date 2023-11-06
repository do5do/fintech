package com.zerobase.api.product.dto

import com.zerobase.domain.domain.ProductInfo
import com.zerobase.domain.domain.ProductList
import com.zerobase.domain.type.OrgType
import com.zerobase.domain.type.ProdType
import io.swagger.annotations.ApiModelProperty

class ProductRequestDto {
    data class ProductRequestInput(
        @ApiModelProperty(example = "001")
        val organizationCode: OrgType,

        @ApiModelProperty(example = "00001")
        val productCode: ProdType,

        @ApiModelProperty(example = "9.9")
        val productMinimumInterest: Double,

        @ApiModelProperty(example = "1.1")
        val productMaximumInterest: Double,

        @ApiModelProperty(example = "상품 1")
        val productName: String
    ) {

        fun toProductInfo(productList: ProductList): ProductInfo {
            return ProductInfo(
                productList, productName, productMinimumInterest, productMaximumInterest
            )
        }

        fun toProductList(): ProductList {
            return ProductList(
                organizationCode, productCode
            )
        }
    }

    data class ProductRequestResponse(
        @ApiModelProperty(example = "00")
        val responseCode: String,

        @ApiModelProperty(example = "success")
        val responseMessage: String
    )
}