package com.liuujun.mshop.auth;

import java.io.IOException;
import java.util.Random;

/**
 * @author zhouyi
 */
public class CodeHelper {

    private static final String RANDOM_ARRAY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static String randomCode(){
        Random random = new Random();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(RANDOM_ARRAY.length());
            sb.append(RANDOM_ARRAY.charAt(index));
        }
        return sb.toString();
    }

    public static String generateImage(String code){
        try {
            return PicGenerate.generate(code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String code = randomCode();
        System.out.println(code);
        System.out.println(generateImage(code));
    }
}
