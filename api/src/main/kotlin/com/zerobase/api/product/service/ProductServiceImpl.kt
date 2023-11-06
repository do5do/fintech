package com.zerobase.api.product.service

import com.zerobase.api.exception.CustomErrorCode.PRODUCT_LIST_ALREADY_EXISTS
import com.zerobase.api.exception.CustomErrorCode.PRODUCT_LIST_NOT_FOUND
import com.zerobase.api.exception.CustomException
import com.zerobase.api.product.dto.ProductDto
import com.zerobase.api.product.dto.ProductRequestDto
import com.zerobase.domain.repository.ProductInfoRepository
import com.zerobase.domain.repository.ProductListRepository
import com.zerobase.domain.type.OrgType
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductServiceImpl(
    private val productListRepository: ProductListRepository,
    private val productInfoRepository: ProductInfoRepository
) : ProductService {

    @Transactional
    @CacheEvict(key = "#productRequestInput", value = ["PRODUCT"], cacheManager = "redisCacheManager")
    override fun productRequest(
        productRequestInput: ProductRequestDto.ProductRequestInput
    ): ProductDto {
        val exists = productListRepository.existsByOrganizationCodeAndProductCode(
            productRequestInput.organizationCode, productRequestInput.productCode
        )

        if (exists) {
            throw CustomException(PRODUCT_LIST_ALREADY_EXISTS)
        }

        val productList = productRequestInput.toProductList()
        productListRepository.save(productList)

        val productInfo = productInfoRepository.save(
            productRequestInput.toProductInfo(productList)
        )
        return ProductDto.fromEntity(productInfo)
    }

    @Cacheable(key = "#organizationCode", value = ["PRODUCT"], cacheManager = "redisCacheManager")
    override fun findProducts(organizationCode: OrgType): List<ProductDto> {
        val productList = productListRepository.findByOrganizationCode(organizationCode)
            ?: throw CustomException(PRODUCT_LIST_NOT_FOUND)

        val productInfos = productInfoRepository.findAllByProductList(productList)
            ?: throw CustomException(PRODUCT_LIST_NOT_FOUND)

        return productInfos.map { productInfo ->
            ProductDto.fromEntity(productInfo)
        }.toList()
    }

}