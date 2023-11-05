package com.zerobase.api.product

import com.zerobase.domain.domain.ProductInfo
import com.zerobase.domain.domain.ProductList
import com.zerobase.domain.type.OrgType
import com.zerobase.domain.type.ProdType

class ProductRequestDto {
    data class ProductRequestInputDto(
        val organizationCode: OrgType,
        val productCode: ProdType,
        val productMinimumInterest: Double,
        val productMaximumInterest: Double,
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
        val responseCode: String,
        val responseMessage: String
    )
}