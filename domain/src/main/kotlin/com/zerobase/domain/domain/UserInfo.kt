package com.zerobase.domain.domain

import com.zerobase.domain.annotation.Encrypt
import javax.persistence.*

@Entity
@Table(name = "USR_INFO")
class UserInfo(
    @Column(name = "usr_key")
    val userKey: String,

    @Encrypt
    @Column(name = "usr_reg_num")
    val userRegistrationNumber: String,

    @Column(name = "usr_nm")
    val userName: String,

    @Column(name = "usr_icm_amt")
    val userIncomeAmount: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}

// gradle 설정에 plugin 설정으로 인해 컬럼에 대한 초기값을 넣어주지 않아도 된다.