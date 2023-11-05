package com.zerobase.api.user

import com.zerobase.api.exception.CustomErrorCode
import com.zerobase.api.exception.CustomException
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
        userRequestInputDto: UserRequestDto.UserRequestInputDto
    ): UserRequestDto.UserResponseDto {
        val userKey = generateKey.generateUserKey()
        userInfoRepository.save(
            userRequestInputDto.toEntity(userKey)
        )
        return UserRequestDto.UserResponseDto(userKey)
    }

    override fun getUser(userKey: String): UserDto {
        val userInfo = userInfoRepository.findByUserKey(userKey)
            ?: throw CustomException(CustomErrorCode.USER_NOT_FOUND)

        return UserDto.fromEntity(userInfo)
    }

}