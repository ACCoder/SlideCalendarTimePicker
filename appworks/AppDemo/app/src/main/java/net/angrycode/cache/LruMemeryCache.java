package com.yyw.cloudoffice.UI.Task.Cache;

import java.util.Collection;

public class LruMemeryCache<T> implements MemoryCache<T> {

    private final LruCache<String, T> lruCache;

    private final int maxSize;


    /** @param maxSize Maximum sum of the sizes of the T in this cache */
    public LruMemeryCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        this.lruCache = new LruCache<>(maxSize);
    }

    /**
     * Returns the T for {@code key} if it exists in the cache. If a Bitmap was returned, it is moved to the head
     * of the queue. This returns null if a Bitmap is not cached.
     */
    @Override
    public final T get(String key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }

        return lruCache.get(key);
    }

    /** Caches {@code T} for {@code key}. The Bitmap is moved to the head of the queue. */
    @Override
    public final boolean put(String key, T value) {
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }

        lruCache.put(key, value);

        return true;
    }

    /** Removes the entry for {@code key} if it exists. */
    @Override
    public final T remove(String key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }

        T previous = lruCache.remove(key);
        return previous;
    }

    @Override
    public Collection<String> keys() {
        return lruCache.snapshot().keySet();
    }

    @Override
    public void clear() {
        lruCache.trimToSize(-1); // -1 will evict 0-sized elements
    }

    @Override
    public synchronized final String toString() {
        return String.format("LruCache[maxSize=%d]", maxSize);
    }
}
