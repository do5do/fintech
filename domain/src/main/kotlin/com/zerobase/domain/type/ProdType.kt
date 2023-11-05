package com.zerobase.domain.type

enum class ProdType(
    val code: String,
    val orgName: String
) {
    BK("00001", "한국은행"),
    KDB("00002", "산업은행"),
    IBK("00003", "기업은행");

    companion object {
        fun fromCode(code: String): ProdType {
            return ProdType.values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("Format $code is illegal")
        }
    }
}