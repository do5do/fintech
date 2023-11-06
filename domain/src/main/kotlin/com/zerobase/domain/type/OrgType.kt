package com.zerobase.domain.type

enum class OrgType(
    val code: String,
) {
    BK("001"),
    KDB("002"),
    IBK("003");

    companion object {
        fun fromCode(code: String): OrgType {
            return values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("Format $code is illegal")
        }
    }
}