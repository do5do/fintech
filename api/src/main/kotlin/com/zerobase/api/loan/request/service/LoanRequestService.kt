package com.zerobase.api.loan.request.service

import com.zerobase.api.loan.request.dto.LoanRequestDto
import com.zerobase.api.loan.request.dto.UserInfoDto
import com.zerobase.domain.domain.UserInfo

interface LoanRequestService {

    fun loanRequestMain(
        loanRequestInputDto: LoanRequestDto.LoanRequestInputDto
    ): LoanRequestDto.LoanRequestResponseDto

    fun saveUserInfo(
        userInfoDto: UserInfoDto
    ): UserInfo

    fun loanRequestReview(userInfoDto: UserInfoDto)
}