package com.zerobase.domain.domain

import javax.persistence.*

@Entity
@Table(name = "PRODUCT_INFO")
class ProductInfo(
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_li_id")
    var productList: ProductList,

    @Column(name = "prod_nm")
    val productName: String,

    @Column(name = "prod_min_intr")
    val productMinimumInterest: Double,

    @Column(name = "prod_max_intr")
    val productMaximumInterest: Double
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}