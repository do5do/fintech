package com.zerobase.api.loan.review.controller

import com.zerobase.api.loan.review.dto.LoanReviewDto
import com.zerobase.api.loan.review.service.LoanReviewServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fintech/api/v1")
class LoanReviewController(
    private val loanReviewService: LoanReviewServiceImpl
) {

    @GetMapping("/review/{userKey}")
    fun getReviewDate(
        @PathVariable userKey: String
    ): ResponseEntity<LoanReviewDto.LoanReviewResponseDto> {
        return ResponseEntity.ok(
            loanReviewService.loanReviewMain(userKey)
        )
    }
}