package com.abhinav;

public class QuickSort {
    public static String[] quickSort(String[] directoryDataArray, long left, long right) {
        if (left < right){
            long pivotIndex = partition(directoryDataArray, left, right);
            quickSort(directoryDataArray, left, pivotIndex - 1);
            quickSort(directoryDataArray, pivotIndex + 1, right);
        }
        return directoryDataArray;
    }

    private static long partition(String[] directoryDataArray, long left, long right) {
        String pivot = directoryDataArray[Math.toIntExact(right)];
        long partitionIndex = left;

        for (long i = left; i < right; i++){
            try {
                if (directoryDataArray[Math.toIntExact(i)].compareTo(pivot) > 0) {
                    swap(directoryDataArray, i, partitionIndex);
                    partitionIndex++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        swap(directoryDataArray, partitionIndex, right);

        return partitionIndex;
    }

    private static void swap(String[] directoryDataArray, long i, long j) {
        String temp = directoryDataArray[Math.toIntExact(i)];
        directoryDataArray[Math.toIntExact(i)] = directoryDataArray[Math.toIntExact(j)];
        directoryDataArray[Math.toIntExact(j)] = temp;
    }
}
