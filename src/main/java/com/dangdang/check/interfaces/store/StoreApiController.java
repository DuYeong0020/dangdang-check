package com.dangdang.check.interfaces.store;

import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.store.StoreCommand;
import com.dangdang.check.domain.store.StoreInfo;
import com.dangdang.check.domain.store.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreService storeService;

    @PostMapping("/api/stores")
    public CommonResponse<StoreDto.RegisterStoreResponse> registerStore(@Login String loginId, @RequestBody @Valid StoreDto.RegisterStoreRequest request) {
        StoreCommand.RegisterStoreRequest command = request.toCommand(loginId);
        StoreInfo storeInfo = storeService.registerStore(command);
        StoreDto.RegisterStoreResponse response = new StoreDto.RegisterStoreResponse(storeInfo);
        return CommonResponse.success(response);
    }
}
