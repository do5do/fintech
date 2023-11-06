package com.zerobase.api.user.service

import com.zerobase.api.exception.CustomException
import com.zerobase.api.exception.ErrorCode
import com.zerobase.api.user.dto.UserDto
import com.zerobase.api.user.dto.UserRequestDto
import com.zerobase.api.util.GenerateKey
import com.zerobase.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository
) : UserService {

    @Transactional
    override fun userRequest(
        userRequestInput: UserRequestDto.UserRequestInput
    ): UserRequestDto.UserKeyResponse {
        val userKey = generateKey.generateUserKey()
        userInfoRepository.save(
            userRequestInput.toEntity(userKey)
        )
        return UserRequestDto.UserKeyResponse(userKey)
    }

    override fun getUser(userKey: String): UserDto {
        val userInfo = userInfoRepository.findByUserKey(userKey)
            ?: throw CustomException(ErrorCode.USER_NOT_FOUND)

        return UserDto.fromEntity(userInfo)
    }

}