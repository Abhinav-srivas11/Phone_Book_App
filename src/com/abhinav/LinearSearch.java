package com.abhinav;

public class LinearSearch {
    public static int linearSearch(String[] directoryDataArray, String[] findDataArray) {
        int counter = 0;
        for (String s : findDataArray) {
            for (String value : directoryDataArray) {
                if (s.equals(value)) {
                    counter++;
                    break;
                }
            }
        }
        return counter;
    }
}
