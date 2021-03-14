package com.abhinav;

public class BubbleSort {
    public static String[] bubbleSort(String[] directoryDataArray, long linearSearchTime) {
        long startTimeBubbleSort = System.currentTimeMillis();
        String[] array = directoryDataArray.clone();
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {

                if ((System.currentTimeMillis() - startTimeBubbleSort) >= 5 * linearSearchTime) {
                    return null;
                }

                if (array[j].compareTo(array[j + 1]) > 0) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        return array;
    }
}
