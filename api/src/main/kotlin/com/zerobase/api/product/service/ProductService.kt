package com.zerobase.api.product.service

import com.zerobase.api.product.dto.ProductDto
import com.zerobase.api.product.dto.ProductRequestDto
import com.zerobase.domain.type.OrgType

interface ProductService {

    fun productRequest(
        productRequestInput: ProductRequestDto.ProductRequestInput
    ): ProductDto

    fun findProducts(
        organizationCode: OrgType
    ): List<ProductDto>
}