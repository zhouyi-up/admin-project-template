package com.liuujun.mshop.auth;

import java.util.Random;

/**
 * @author zhouyi
 */
public class RandomUtils {
    private static String RANDOM_ARRAY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static String string(int len){
        Random random = new Random();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(RANDOM_ARRAY.length());
            sb.append(RANDOM_ARRAY.charAt(index));
        }
        return sb.toString();
    }
}
