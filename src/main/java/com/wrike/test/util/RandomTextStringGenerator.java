package com.wrike.test.util;

import java.util.Random;

public class RandomTextStringGenerator {

    private static Random random = new Random();

    public static String getRandomTextString() throws IllegalArgumentException {
        int defaultMinLenght = 5;
        int defaultMaxLenght = 15;
        return getRandomTextString(defaultMinLenght, defaultMaxLenght);
    }

    public static String getRandomTextString(int length) throws IllegalArgumentException {
        return getRandomTextString(length,length);
    }

    public static String getRandomTextString(int minLength, int maxLength) throws IllegalArgumentException {
        if (minLength < 0 || maxLength < 0 || maxLength < minLength)
            throw new IllegalArgumentException();
        return random.ints('A', 'Z')
                .limit(random.nextInt(maxLength - minLength) + minLength)
                .map(e -> random.nextBoolean() ? e : e + 0x20)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }
}
