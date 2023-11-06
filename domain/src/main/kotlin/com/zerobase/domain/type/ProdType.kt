package com.zerobase.domain.type

enum class ProdType(
    val code: String,
) {
    BK("00001"),
    KDB("00002"),
    IBK("00003");

    companion object {
        fun fromCode(code: String): ProdType {
            return ProdType.values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("Format $code is illegal")
        }
    }
}