package com.zerobase.domain.type

enum class OrgType(
    val code: String,
    val orgName: String
) {
    BK("001", "한국은행"),
    KDB("002", "산업은행"),
    IBK("003", "기업은행");

    companion object {
        fun fromCode(code: String): OrgType {
            return values().firstOrNull {
                it.code == code
            } ?: throw IllegalArgumentException("Format $code is illegal")
        }
    }
}