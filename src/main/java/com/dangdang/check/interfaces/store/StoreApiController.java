package com.dangdang.check.interfaces.store;

import com.dangdang.check.common.argumentresolver.Login;
import com.dangdang.check.common.response.CommonResponse;
import com.dangdang.check.domain.store.StoreCommand;
import com.dangdang.check.domain.store.StoreInfo;
import com.dangdang.check.domain.store.StoreService;
import com.dangdang.check.domain.store.StoreSummaryInfo;
import com.dangdang.check.infrastrucure.store.StoreCriteria;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/stores/{storeId}")
    public CommonResponse<StoreDto.GetStoreResponse> getStoreById(@PathVariable Long storeId) {
        StoreInfo storeInfo = storeService.getStoreById(storeId);
        StoreDto.GetStoreResponse response = new StoreDto.GetStoreResponse(storeInfo);
        return CommonResponse.success(response);
    }

    @GetMapping("/api/stores")
    public CommonResponse<Page<StoreDto.GetStoresResponse>> getStoresByCriteria(@Valid StoreDto.GetStoresRequest request, Pageable pageable) {
        StoreCriteria.GetStores criteria = request.toCriteria(pageable);
        Page<StoreSummaryInfo> storeSummaryPage = storeService.getStoresByCriteria(criteria);
        Page<StoreDto.GetStoresResponse> response = storeSummaryPage.map(StoreDto.GetStoresResponse::new);
        return CommonResponse.success(response);
    }

    @PatchMapping("/api/stores/{storeId}/approve")
    public CommonResponse<StoreDto.ApproveStoreResponse> approveStore(@PathVariable Long storeId) {
        StoreInfo storeInfo = storeService.approveStore(storeId);
        StoreDto.ApproveStoreResponse response = new StoreDto.ApproveStoreResponse(storeInfo);
        return CommonResponse.success(response);
    }

    @PatchMapping("/api/stores/{storeId}/reject")
    public CommonResponse<StoreDto.RejectStoreResponse> rejectStore(@PathVariable Long storeId, @RequestBody StoreDto.RejectStoreRequest request) {
        StoreInfo storeInfo = storeService.rejectStore(storeId, request.getReason());
        StoreDto.RejectStoreResponse response = new StoreDto.RejectStoreResponse(storeInfo);
        return CommonResponse.success(response);
    }

    @PatchMapping("/api/stores/{storeId}/soft-delete")
    public CommonResponse<Boolean> softDeleteStore(@PathVariable Long storeId) {
        storeService.softDeleteStore(storeId);
        return CommonResponse.success(true);
    }
}
