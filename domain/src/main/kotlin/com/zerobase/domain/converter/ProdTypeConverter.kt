package com.zerobase.domain.converter

import com.zerobase.domain.type.ProdType
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class ProdTypeConverter : AttributeConverter<ProdType, String> {
    override fun convertToDatabaseColumn(attribute: ProdType?): String? {
        if (attribute == null) {
            return null
        }
        return attribute.code
    }

    override fun convertToEntityAttribute(dbData: String?): ProdType? {
        if (dbData == null) {
            return null
        }
        return ProdType.fromCode(dbData)
    }
}