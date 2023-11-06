package com.zerobase.domain.domain

import com.zerobase.domain.type.OrgType
import com.zerobase.domain.converter.OrgTypeConverter
import com.zerobase.domain.type.ProdType
import com.zerobase.domain.converter.ProdTypeConverter
import javax.persistence.*

@Entity
@Table(name = "PRODUCT_LIST",
    uniqueConstraints = [UniqueConstraint(columnNames = ["org_cd", "prod_cd"])]
)
class ProductList(
    @Column(name = "org_cd")
    @Convert(converter = OrgTypeConverter::class)
    val organizationCode: OrgType,

    @Column(name = "prod_cd")
    @Convert(converter = ProdTypeConverter::class)
    val productCode: ProdType
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @OneToOne(mappedBy = "productList")
    var productInfo: ProductInfo? = null

    fun setProduct(productInfo: ProductInfo) {
        this.productInfo = productInfo
    }
}