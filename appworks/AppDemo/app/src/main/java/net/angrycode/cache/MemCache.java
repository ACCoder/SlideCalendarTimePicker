/*
 * Copyright 1999-2101 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.angrycode.cache;


import com.squareup.picasso.LruCache;

import java.util.Map;

/**
 * Mem level Cache
 * @author zhe.yangz 2012-3-30 下午03:23:19
 */
public interface MemCache<K, V> {

    public V get(K key);
    public V put(K key, V value);
    public V remove(K key);

    /**
     * Clear all kvs of this MemCache
     */
    public void clear();

    /**
     * Get a snapshot of cache
     */
    public Map<K, V> snapshot();
    
}
