package com.zerobase.domain.type

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class OrgTypeConverter : AttributeConverter<OrgType, String> {
    override fun convertToDatabaseColumn(attribute: OrgType?): String? {
        if (attribute == null) {
            return null
        }
        return attribute.code
    }

    override fun convertToEntityAttribute(dbData: String?): OrgType? {
        if (dbData == null) {
            return null
        }
        return OrgType.fromCode(dbData)
    }
}