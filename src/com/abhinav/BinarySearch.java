package com.abhinav;

public class BinarySearch {

    public static boolean binarySearch(String[] directoryDataArray, String findDataArrayElement){
        int left = 0; int right = directoryDataArray.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (directoryDataArray[mid].equals(findDataArrayElement)) {
                return true;
            } else if (directoryDataArray[mid].compareTo(findDataArrayElement) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
