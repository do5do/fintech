package com.zerobase.domain.repository

import com.zerobase.domain.domain.ProductList
import com.zerobase.domain.type.OrgType
import com.zerobase.domain.type.ProdType
import org.springframework.data.jpa.repository.JpaRepository

interface ProductListRepository : JpaRepository<ProductList, Long> {
    fun existsByOrganizationCodeAndProductCode(organizationCode: OrgType, productCode: ProdType): Boolean

    fun findByOrganizationCode(organizationCode: OrgType): ProductList?
}