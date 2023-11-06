package com.zerobase.api.product.controller

import com.zerobase.api.type.SuccessCode
import com.zerobase.api.product.dto.GetProductInfoResponse
import com.zerobase.api.product.dto.ProductRequestDto
import com.zerobase.api.product.service.ProductServiceImpl
import com.zerobase.domain.type.OrgType
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(tags = ["product-information-controller-impl"], description = "상품 정보 API")
@RestController
@RequestMapping("/fintech/v1/product")
class ProductController(
    private val productService: ProductServiceImpl
) {
    @ApiOperation(value = "상품 정보 수신 API", notes = "금융사로부터 상품 정보를 받는 API")
    @PostMapping("/information")
    fun productRequest(
        @RequestBody productRequestInput: ProductRequestDto.ProductRequestInput
    ): ResponseEntity<ProductRequestDto.ProductRequestResponse> {
        productService.productRequest(productRequestInput)
        return ResponseEntity.ok(
            ProductRequestDto.ProductRequestResponse(
                SuccessCode.IS_OK.responseCode,
                SuccessCode.IS_OK.responseMessage
            )
        )
    }

    @ApiOperation(value = "상품 정보 조회 API", notes = "상품 정보를 조회하는 API")
    @ApiImplicitParam(name = "organizationCode", value = "기관 코드")
    @GetMapping("/{organizationCode}")
    fun getProductInfo(@PathVariable organizationCode: OrgType)
    : ResponseEntity<GetProductInfoResponse> {
        val products = productService.findProducts(organizationCode)
        return ResponseEntity.ok(
            GetProductInfoResponse(products,
            SuccessCode.IS_OK.responseCode, SuccessCode.IS_OK.responseMessage)
        )
    }
}