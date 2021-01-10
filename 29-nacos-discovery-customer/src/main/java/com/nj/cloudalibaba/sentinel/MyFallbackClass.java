package com.nj.cloudalibaba.sentinel;

public class MyFallbackClass {

    /**
     * 该方法一定要是static方法
     *
     * @param a
     * @param b
     * @return
     */
    public static String fallback(String a, String b) {
        System.out.println("Fall back--> " + a + "--" + b);
        return "Fall back.";
    }

    public static String fallback2(String a, String b) {
        System.out.println("Fall back 2--> " + a + "--" + b);
        return "Fall back 2.";
    }

}