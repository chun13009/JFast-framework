package com.jfast.cache;

import java.net.URL;
import java.util.List;

import com.jfast.log.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheStore {

	private static volatile CacheManager cacheManager;
	private static final Logger log = Logger.getLogger(CacheStore.class);

	public static void init(CacheManager cacheManager) {
		CacheStore.cacheManager = cacheManager;
	}

	static {
		try {
			URL url=CacheStore.class.getResource("ehcache.xml");
			if (url!=null) {
				cacheManager = CacheManager.create(url);
			}else {
				cacheManager = CacheManager.create();
			}
			log.info("ehcache初始化");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("ehcache初始化失败");
		}
	}

	public static CacheManager getCacheManager() {
		if (cacheManager == null) {
			cacheManager = CacheManager.create();
		}
		return cacheManager;
	}

	public static Cache getOrAddCache(String cacheName) {
		Cache cache = getCacheManager().getCache(cacheName);
		if (cache == null) {
			synchronized (getCacheManager()) {
				cache = getCacheManager().getCache(cacheName);
				if (cache == null) {
					log.warn("Could not find cache config [" + cacheName + "], using default.");
					getCacheManager().addCacheIfAbsent(cacheName);
					cache = getCacheManager().getCache(cacheName);
					log.debug("Cache [" + cacheName + "] started.");
				}
			}
		}
		return cache;
	}

	public static void put(String cacheName, Object key, Object value) {
		put(cacheName, key, value, false);
	}
	public static void put(String cacheName, Object key, Object value,boolean isflush) {
		getOrAddCache(cacheName).put(new Element(key, value));
		if (isflush) {
			getOrAddCache(cacheName).flush();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String cacheName, Object key) {
		Element element = getOrAddCache(cacheName).get(key);
		return element != null ? (T) element.getObjectValue() : null;
	}

	@SuppressWarnings("rawtypes")
	public static List getKeys(String cacheName) {
		return getOrAddCache(cacheName).getKeys();
	}

	public static void remove(String cacheName, Object key) {
		getOrAddCache(cacheName).remove(key);
	}

	public static void removeAll(String cacheName) {
		getOrAddCache(cacheName).removeAll();
	}
	
	public static void shutdown() {
		cacheManager.shutdown();
	}
	
}
