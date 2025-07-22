package com.arthur.schedulingApi.usecases;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClearServiceCache {

    @CacheEvict(value = "SERVICE_CACHE", allEntries = true)
    public void clearAllCache() {
        log.info("### Limpando todo o cache SERVICE_CACHE ###");
    }

}
