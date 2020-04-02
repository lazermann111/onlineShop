package com.skillsup.helpers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {

    private int BUCKET_NUMBER = 2;
    private Node<K, V>[] table = new Node[BUCKET_NUMBER];
    private int size;

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public V get(Object key) {
        int modCount = key.hashCode() % BUCKET_NUMBER;
        Node<K,V> last = table[modCount];
        if(last == null)
        {
            return null;
        }
        if(last.key.equals(key))
        {
            return last.value;
        }
        while (last.next != null) {
            last = last.next;
            if(last.key.equals(key))
            {
                return last.value;
            }
        }
        return null;
    }
    @Override
    public V put(K key, V value) {
        int modCount = key.hashCode() % BUCKET_NUMBER;
        if (table[modCount] == null)
        {
            table[modCount] = new Node<>(key, value);
        }
        else {
            Node<K,V> last = table[modCount];
            if(last.key.equals(key))
            {
                return last.value;
            }
            while (last.next != null) {
                last = last.next;
                if(last.key.equals(key))
                {
                    last.value = value;
                    return value;
                }
            }
            last.next = new Node<>(key, value);
        }
        size++;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object o) {
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        return false;
    }




    @Override
    public V remove(Object key) {
        int modCount = key.hashCode() % BUCKET_NUMBER;
        Node<K,V> prevNode = table[modCount];
        if(prevNode == null)
        {
            return null;
        }
        Node<K,V> currentNode = prevNode.next;
        //todo remove do .. while
        do{
            if(currentNode.key.equals(key))
            {
                prevNode.next = currentNode.next;
                size--;
                return currentNode.value;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        while (currentNode.next != null);
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
