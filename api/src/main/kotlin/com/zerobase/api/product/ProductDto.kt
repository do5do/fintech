package com.zerobase.api.product

import com.zerobase.domain.domain.ProductInfo
import com.zerobase.domain.type.OrgType
import com.zerobase.domain.type.ProdType

data class ProductDto(
    val organizationCode: OrgType,
    val productCode: ProdType,
    val productMinimumInterest: Double,
    val productMaximumInterest: Double,
    val productName: String
) {
    companion object {
        fun fromEntity(productInfo: ProductInfo): ProductDto {
            return ProductDto(
                productInfo.productList.organizationCode,
                productInfo.productList.productCode,
                productInfo.productMinimumInterest,
                productInfo.productMaximumInterest,
                productInfo.productName
            )
        }
    }
}