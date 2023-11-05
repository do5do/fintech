package com.zerobase.api.user

import com.zerobase.api.dto.CommonResponseDto
import com.zerobase.api.dto.SuccessCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/fintech/v1/user")
class UserController(
    private val userService: UserServiceImpl
) {

    @PostMapping("/information")
    fun userRequest(
        @RequestBody userRequestInputDto: UserRequestDto.UserRequestInputDto
    ): ResponseEntity<CommonResponseDto> {
        val userRequestDto = userService.userRequest(userRequestInputDto)
        return ResponseEntity.ok(
            CommonResponseDto(userRequestDto,
                SuccessCode.IS_OK.responseCode, SuccessCode.IS_OK.responseMessage)
        )
    }

    @GetMapping("/{userKey}")
    fun getUser(@PathVariable userKey: String): ResponseEntity<CommonResponseDto> {
        val userDto = userService.getUser(userKey)
        return ResponseEntity.ok(
            CommonResponseDto(userDto,
                SuccessCode.IS_OK.responseCode, SuccessCode.IS_OK.responseMessage)
        )
    }
}