package com.gairola;

import java.util.*;
import java.util.Map.Entry;

import java.util.stream.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        String input = "HELLO";
        char[] chars = input.toCharArray();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char ch : chars) {
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {
                int value = map.get(ch);
                map.put(ch, value + 1);
            }

        }
        System.out.println(map);

        // ******************************* */
        String input1 = "AABCDBE";
        char[] chars1 = input1.toCharArray();
        Map<Character, Integer> map1 = new HashMap<Character, Integer>();
        for (int i = 0; i < input1.length(); i++) {
            boolean unique = true;
            for (int j = 0; j < input1.length(); j++) {
                {
                    if (i != j && input1.charAt(i) == input1.charAt(j))
                        unique = false;

                }
            }
            if (unique) {
                System.out.println(input1.charAt(i));
                break;
            }

        }

        // String input1 = "AABCDBE";
        // char[] chars1 = input1.toCharArray();

        for (char ch : chars1) {

            if (!map1.containsKey(ch)) {
                map1.put(ch, 1);
            } else {
                int value = map1.get(ch);
                map1.put(ch, value + 1);
            }
        }
        System.out.println(map1);
        System.out.println("*******************************************");
        for (Entry<Character, Integer> entrySet : map1.entrySet()) {
            if (entrySet.getValue() == 1) {
                System.out.println(entrySet.getKey());
                break;
            }

        }
        System.out.println("*******************************************");
        String Data = "HELLO";
        // ******************************************* */
        StringBuffer sb = new StringBuffer();
        for (int i = Data.length() - 1; i >= 0; i--) {
            System.out.println(Data.charAt(i));
            sb.append(Data.charAt(i));
        }

        System.out.println(sb);
        System.out.println("*******************************************");

        // int count = Stream.of(1, 2, 3, 4, 5).filter(i-> i==2).
        // .filter(i -> i < 4) // Intermediate Operation filter
        // .count();

        // Stream.of(1, 2, 3, 4, 5).filter(i-> i==2).f
    }

}
