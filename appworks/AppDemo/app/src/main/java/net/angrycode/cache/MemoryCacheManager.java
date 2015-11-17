package com.yyw.cloudoffice.UI.Task.Cache;

import java.util.Collection;

/**
 * Created by 阳仔 on 15/5/26.
 */
public class MemoryCacheManager implements MemoryCache<Object>{

    MemoryCache<Object> memoryCache;
    private static class InstanceHolder{
        private static MemoryCacheManager sCacheManager = new MemoryCacheManager();
    }

    private MemoryCacheManager() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 4;
        memoryCache = new LruMemeryCache<>(cacheSize);
    }
    public static MemoryCacheManager getMemoryCacheManager(){
        return InstanceHolder.sCacheManager;
    }

    @Override
    public boolean put(String key, Object value) {
        return memoryCache.put(key,value);
    }

    @Override
    public Object get(String key) {
        return memoryCache.get(key);
    }

    @Override
    public Object remove(String key) {
        return memoryCache.remove(key);
    }

    @Override
    public Collection<String> keys() {
        return memoryCache.keys();
    }

    @Override
    public void clear() {
        memoryCache.clear();
    }
}
