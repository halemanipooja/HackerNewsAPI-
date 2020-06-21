package com.paytminsider.hackernews;

import java.util.Map;
import java.util.WeakHashMap;


/*
 *  Cache class provides logic to store the data for 10 minutes
*/

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
	
	
	/*
	 * method to store the data from hacker news api in a map object
	 * 
	 * @param key - key for the map object
	 * @param value - object data from hackernew api
	*/
	public void put(String key, Object value) {
		store.put(key, new Item(value));
	}
	
	
	/*
	 * method to return the stored data from hacker news api
	 * 
	 * @param key - key for the map object
	 * @return item - value from the map based on the key
	*/
	public Object get(String key) {
		Item item = getItem(key);
		return item == null ? null : item.payload;
	}
	
	/*
	 * method to return the stored data from hackernews api based on the time
	 * 
	 * @param key - key for the map object
	 * @return item - value from the map based on the key and the persistence time
	*/
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
	
	
	/*
	 * Item class containing 2 attributes birth and object
	 * birth - to hold the creation time of the object
	 * object - to hold the data from hackernews api
	*/
	private static class Item {
		long birth;
		Object payload;
		Item(Object payload) {
			this.birth = System.currentTimeMillis();
			this.payload = payload;
		}
	}
}