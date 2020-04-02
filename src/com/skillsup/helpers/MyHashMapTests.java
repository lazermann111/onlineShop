package com.skillsup.helpers;

import java.util.HashMap;
import java.util.Map;

public class MyHashMapTests {
    public static void main(String[] args) {
        testPutAndSize(new HashMap());
        testPutAndSize(new MyHashMap());

        //testDuplicates();
       // testRemove();
       // testClear();
        System.out.println("Tests passed!");
    }

    public static void testPutAndSize(Map map)
    {
        map.put("1", 1232);
        map.put("8", 1232);
        map.put("10", 122);
        assert map.size() == 3;

        map.put("2", 3);
        assert map.size() == 4;

        //no duplicate keys allowed
        map.put("2", 5);
        assert map.size() == 4;

        //last stored value retains
        assert map.get("2").equals(5);
    }

    public static void testDuplicates()
    {
        Map<Integer, Integer> map = new MyHashMap<>();
        map.put(4, 3);
        map.put(2, 3);
        assert map.size() == 2;

        //no duplicate keys allowed
        map.put(2, 5);
        assert map.size() == 2;

        //last stored value retains
        assert map.get(2).equals(5);
    }

    public static void testRemove()
    {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2123);
        map.put(3, 2345);
        map.put(5, 254376);
        assert map.size() == 3;

        map.remove(3);
        assert map.size() == 2;

        assert map.get(1).equals(2123);
        assert map.get(5).equals(254376);
    }

    public static void testClear()
    {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 2);
        map.put("2", 2);
        map.put("3", 2);
        map.clear();
        assert map.size() == 0;
    }
}
