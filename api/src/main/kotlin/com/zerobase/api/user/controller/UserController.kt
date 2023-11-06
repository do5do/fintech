package com.zerobase.api.user.controller

import com.zerobase.api.dto.SuccessCode
import com.zerobase.api.user.dto.GetUserResponse
import com.zerobase.api.user.dto.UserRequestDto
import com.zerobase.api.user.service.UserServiceImpl
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Api(tags = ["user-information-controller-impl"], description = "유저 정보 수신 API")
@RestController
@RequestMapping("/fintech/v1/user")
class UserController(
    private val userService: UserServiceImpl
) {

    @ApiOperation(value = "유저 정보 수신 API", notes = "유저 정보를 받는 API")
    @PostMapping("/information")
    fun userRequest(
        @RequestBody userRequestInput: UserRequestDto.UserRequestInput
    ): ResponseEntity<UserRequestDto.UserResponse> {
        val userKeyResponse = userService.userRequest(userRequestInput)
        return ResponseEntity.ok(
            UserRequestDto.UserResponse(userKeyResponse,
                SuccessCode.IS_OK.responseCode, SuccessCode.IS_OK.responseMessage)
        )
    }

    @ApiOperation(value = "유저 정보 조회 API", notes = "유저 정보를 조회하는 API")
    @ApiImplicitParam(name = "userKey", value = "유저 식별 키")
    @GetMapping("/{userKey}")
    fun getUser(@PathVariable userKey: String): ResponseEntity<GetUserResponse> {
        val userDto = userService.getUser(userKey)
        return ResponseEntity.ok(
            GetUserResponse(userDto,
                SuccessCode.IS_OK.responseCode, SuccessCode.IS_OK.responseMessage)
        )
    }
}