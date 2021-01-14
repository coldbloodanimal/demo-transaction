package com.example.sys;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Factory;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 这是一个Map的代理类，可以和普通Map一样使用
 * 该类不对Map原有方法做处理，
 * 只添加方法，增强Map
 * 判空、getString、getDate
 * @author zw
 * @date 2021年1月14日
 */
public class ProxyMap<K,V> implements Map<K,V>{

    private Map<K,V> map;

    public ProxyMap(Map<K, V> map) {
        this.map = map;
    }

    /***
     * 创建要给ProxyMap对象
     * 这是一个泛型方法，也是一个工厂方法
     * @param map
     * @return
     */
    public static <K, V> ProxyMap<K, V> getInstance(Map<K, V> map){
        return new ProxyMap<>(map);
    }

    /***
     * 判断map对应键的值是否为空
     * @param key
     * @return
     */
    public boolean isValueEmpty(Object key){
        V value=map.get(key);
        return ObjectUtils.isEmpty(value);
    }
    /***
     * 获得字符串，如果是空返回null，如果是对象，调用了它的toString
     * @param key
     * @return
     */
    public String getString(Object key) {
        V value=map.get(key);
        if(value==null){
            return null;
        }else{
            return value.toString();
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
        return map.get(key);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
