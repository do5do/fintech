package com.zerobase.domain.repository

import com.zerobase.domain.domain.ProductInfo
import com.zerobase.domain.domain.ProductList
import org.springframework.data.jpa.repository.JpaRepository

interface ProductInfoRepository : JpaRepository<ProductInfo, Long> {
    fun findAllByProductList(productList: ProductList): List<ProductInfo>?
}