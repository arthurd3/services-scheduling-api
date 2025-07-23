package com.arthur.schedulingApi.usecases;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClearServiceCache {

    @CacheEvict(value = "SERVICE_CACHE", allEntries = true)
    public boolean clearServiceCache() {
        log.info("### Cache SERVICE_CACHE limpo com sucesso ###");
        return true;
    }

    @CacheEvict(value = "USER_CACHE", allEntries = true)
    public boolean clearUserCache() {
        log.info("### Cache USER_CACHE limpo com sucesso ###");
        return true;
    }

    @CacheEvict(value = {"SERVICE_CACHE", "USER_CACHE"}, allEntries = true)
    public boolean clearAllCaches() {
        log.info("### Todos os caches foram limpos ###");
        return true;
    }

    @CacheEvict(value = "SERVICE_CACHE", key = "#serviceId")
    public boolean clearServiceById(Long serviceId) {
        log.info("### Cache limpo para service ID: {} ###", serviceId);
        return true;
    }

}
