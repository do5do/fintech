package com.zerobase.api.product

import com.zerobase.api.dto.CommonResponseDto
import com.zerobase.api.dto.SuccessCode
import com.zerobase.domain.type.OrgType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/fintech/v1/product")
class ProductController(
    private val productService: ProductServiceImpl
) {
    @PostMapping("/information")
    fun productRequest(
        @RequestBody productRequestInputDto: ProductRequestDto.ProductRequestInputDto
    ): ResponseEntity<ProductRequestDto.ProductRequestResponse> {
        productService.productRequest(productRequestInputDto)
        return ResponseEntity.ok(
            ProductRequestDto.ProductRequestResponse(
                SuccessCode.IS_OK.responseCode,
                SuccessCode.IS_OK.responseMessage
            )
        )
    }

    @GetMapping("/{organizationCode}")
    fun getProductInfo(@PathVariable organizationCode: OrgType)
    : ResponseEntity<CommonResponseDto> {
        val products = productService.findProducts(organizationCode)
        return ResponseEntity.ok(
            CommonResponseDto(products,
            SuccessCode.IS_OK.responseCode, SuccessCode.IS_OK.responseMessage)
        )
    }
}