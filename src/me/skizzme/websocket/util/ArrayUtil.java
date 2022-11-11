package me.skizzme.websocket.util;

import java.util.Arrays;

public class ArrayUtil {

    public static byte[] sub(byte[] array, int start, int end) {
        return Arrays.copyOfRange(array, start, end);
    }

    public static byte[] subFrom(byte[] array, int start) {
        return Arrays.copyOfRange(array, start, array.length);
    }

    public static byte[] subTo(byte[] array, int end) {
        return Arrays.copyOfRange(array, 0, end);
    }

    public static byte[] add(byte[] array1, byte[] array2) {
        byte[] newArray = new byte[array1.length+array2.length];
        System.arraycopy(array1, 0, newArray, 0, array1.length);
        System.arraycopy(array2, 0, newArray, array1.length, array2.length);
        return newArray;
    }

}
