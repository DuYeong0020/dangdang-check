package com.dangdang.check.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Tag(name = "Test API", description = "테스트용 API")
public class TestController {

    @GetMapping("/hello")
    @Operation(summary = "Hello API", description = "간단한 테스트 API로 'Hello, Swagger!'를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "요청 성공")
    @ApiResponse(responseCode = "500", description = "서버 오류 발생")  // 예외 상황 추가
    public String hello() {
        return "Hello, Swagger!";
    }

    @PostMapping("/echo")
    @Operation(summary = "Echo API", description = "입력받은 문자열을 그대로 반환하는 API")
    @ApiResponse(responseCode = "200", description = "요청 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")  // 예외 상황 추가
    public String echo(@Valid @RequestBody int number) {
        return "Echo: " + number;
    }
}
