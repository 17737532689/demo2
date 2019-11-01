package com.example.demo.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class demo3 {

    public static void main(String[] args) {
        HashMap<Integer, String> t1 = new HashMap<>();


        t1.put(1, "第一");
        t1.put(2, "第二");
        t1.put(3, "第三");

       /* for(int i=1;i<=t1.size();i++){
            System.out.println(t1.get(i));
        }*/

        Iterator<Map.Entry<Integer, String>> iterator = t1.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            Integer key = next.getKey();

            String value = next.getValue();

            System.out.println(key + value);
        }

    }
}