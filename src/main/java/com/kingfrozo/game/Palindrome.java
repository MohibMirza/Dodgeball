package com.kingfrozo.game;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Palindrome {

    static class Pair {
        String a;
        String b;
        int count = 0;
    }

    static Map<String, Pair> pairs = new HashMap<String, Pair>();
    static Set<Integer> used = new HashSet<Integer>();

    static int uniquePal = 0;

    public static void main(String[] args) {
        // String[] arr = {"ck", "kc", "nn", "kc"};
        // String[] arr = {"ck", "kc", "ho", "kc"}; // 4 ab ba nn ab ba
        // String[] arr = {"ab", "hu", "ba", "nn"}; // 6
        String[] arr = {"ab", "ba", "nn", "ab", "ba"};


        populatePairs(arr);
        printAnswer();


    }

    public static void populatePairs(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            boolean comparison = false;
            for (int j = 0; j < arr.length; j++) {
                if(i == j) break;

                String a = arr[i];
                String b = arr[j];


                if (a.compareTo(reverseString(b)) == 0
                        && !used.contains(i) && !used.contains(j)) {
                    comparison = true;

                    used.add(i);
                    used.add(j);

                    String key = (a.compareTo(b) > 0) ? a : b;
                    String val = (a.compareTo(b) > 0) ? b : a;

                    if (!pairs.containsKey(key)) {
                        pairs.put(key, new Pair());
                        pairs.get(key).a = key;
                        pairs.get(key).b = val;
                    }

                    Pair pair = pairs.get(key);
                    pair.count+=4;
                    // System.out.println(i + " " + j  + " "+ pair.count);
                }

            }

            if (!comparison && arr[i].charAt(0) == arr[i].charAt(1) && !used.contains(i)) {
                uniquePal = 2;
                used.add(i);
            }


        }
    }

    public static String reverseString(String str){
        char ch[]=str.toCharArray();
        String rev="";
        for(int i=ch.length-1;i>=0;i--){
            rev+=ch[i];
        }
        return rev;
    }

    public static void printAnswer() {
        pairs.forEach((key, pair) -> {
            uniquePal += pair.count;
        } );

        System.out.println(uniquePal);
    }

}
