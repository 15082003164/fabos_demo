package com.yangy.demoorder.dao;

        import com.yangy.demostorage.service.StorageService;
        import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "Storage",fallback = StorageService.class)
public interface StorageFeignClient {
    @PostMapping("/reduceStorage")
    String reduceStorage(@RequestParam String storageId, @RequestParam Integer storageQuantity);
}
