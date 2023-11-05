package com.zerobase.api.product

import com.zerobase.domain.type.OrgType

interface ProductService {

    fun productRequest(
        productRequestInputDto: ProductRequestDto.ProductRequestInputDto
    ): ProductDto

    fun findProducts(
        organizationCode: OrgType
    ): List<ProductDto>
}