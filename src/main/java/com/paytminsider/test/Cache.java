package com.paytminsider.test;

import java.util.Map;
import java.util.WeakHashMap;

public class Cache {
	private long maxAge;
	private Map<String, Item> store;
	
	public Cache() {
		this.maxAge = 1000 * 60;
		this.store = new WeakHashMap<String, Item>();
	}
	

	public Cache(long maxAge, Map<String, Item> store) {
		this.maxAge = maxAge;
		this.store = store;
	}
	
	
	public void put(String key, Object value) {
		store.put(key, new Item(value));
	}
	
	
	public Object get(String key) {
		Item item = getItem(key);
		return item == null ? null : item.payload;
	}
	
	private Item getItem(String key) {
		Item item = (Item) store.get(key);
		if (item == null) {
			return null;
		}
		if (System.currentTimeMillis() - item.birth > maxAge) {
			store.remove(key);
			return null;
		}
		return item;		
	}
	
	private static class Item {
		long birth;
		Object payload;
		Item(Object payload) {
			this.birth = System.currentTimeMillis();
			this.payload = payload;
		}
	}
}