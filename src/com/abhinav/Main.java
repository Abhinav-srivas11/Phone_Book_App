package com.abhinav;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
//-------------------------------------------------------------------------------------------------------------
        String directoryDataString =
                new String(Files.readAllBytes(Paths.get("C:\\Users\\91878\\Downloads\\directory.txt"))).replaceAll("(\\d+ )", "");
        String[] directoryDataArray;
        directoryDataArray = directoryDataString.split("\r\n");
        //in Windows (and many old OSs), the code for end of line is 2 characters, \r\n, in this order
        String findDataString = new String(Files.readAllBytes(Paths.get("C:\\Users\\91878\\Downloads\\find.txt")));
        String[] findDataArray = findDataString.split("\r\n");

        long startTime;
        int counter;
        long linearSearchTime;

// -------------------------------------------------------------------------------------------------
        System.out.println("Start searching (linear search)...");
        startTime = System.currentTimeMillis();
        counter = LinearSearch.linearSearch(directoryDataArray, findDataArray);
        linearSearchTime = System.currentTimeMillis() - startTime;
        System.out.printf("Found %d / 500 entries. Time taken: %s\n", counter, timeConverter(linearSearchTime));
        System.out.println();

// -------------------------------------------------------------------------------------------------
        System.out.println("Start searching (bubble sort + jump search)...");
        startTime = System.currentTimeMillis();
        String[] sortedArray = BubbleSort.bubbleSort(directoryDataArray, linearSearchTime);
//        String[] sortedArray = quickSort(directoryDataArray, 0, directoryDataArray.length-1);
        long bubbleSortTime = System.currentTimeMillis() - startTime;
        if (sortedArray != null) {
            startTime = System.currentTimeMillis();
            counter = JumpSearch.jumpSearch(sortedArray, findDataArray);
            long jumpSearchTime = System.currentTimeMillis() - startTime;
            System.out.printf("Found %d / 500 entries. Time taken: %s\n", counter, timeConverter(bubbleSortTime + jumpSearchTime));
            System.out.print("Sorting time: " + timeConverter(bubbleSortTime) + "\n");
            System.out.print("Searching time: " + timeConverter(jumpSearchTime) + "\n");
        } else {
            startTime = System.currentTimeMillis();
            counter = LinearSearch.linearSearch(directoryDataArray, findDataArray);
            linearSearchTime = System.currentTimeMillis() - startTime;

            System.out.printf("Found %d / 500 entries. Time taken: %s\n", counter, timeConverter(bubbleSortTime + linearSearchTime));
            System.out.printf("Sorting time: %s - STOPPED, moved to linear search\n", timeConverter(bubbleSortTime));
            System.out.printf("Searching time: %s", timeConverter(linearSearchTime));
            System.out.println("");
        }

        System.out.println("Start searching (quick sort + binary search)...");
        long quickSortStartTime = System.currentTimeMillis();
        String[] quickSortedArray = QuickSort.quickSort(directoryDataArray, 0, directoryDataArray.length-1);

        long startBinary = System.currentTimeMillis();
        int countBinary = 0;
        for(String s : findDataArray){
            if (BinarySearch.binarySearch(quickSortedArray, s)) {
                countBinary++;
            }
        }
        long quickSortTime = System.currentTimeMillis() - quickSortStartTime;
        long endBinary = System.currentTimeMillis() - startBinary;
        System.out.printf("Found %d / 500 entries. Time taken: %s\n", countBinary, timeConverter(quickSortTime + endBinary));
        System.out.print("Sorting time: " + timeConverter(quickSortTime) + "\n");
        System.out.print("Searching time: " + timeConverter(endBinary) + "\n");

//-------------------------------------------------------------------------------------------------------
        // Hash Table
//        String directoryStringHash1 = new String(Files.readAllBytes(Paths.get("C:\\Users\\91878\\Downloads\\directory.txt"))).replaceAll("\\d+","");
        String directoryStringHash =
                new String(Files.readAllBytes(Paths.get("C:\\Users\\91878\\Downloads\\directory.txt"))).replaceAll("(\\d+ )", "");
        System.out.println("Start searching (hash table)...");

        String[] hashDataArray = directoryStringHash.split("\r\n");
        System.out.println("\n \n \n hashDataArr size " + hashDataArray.length);
//        HashTable.HashTableImplementation tableImplementation = new HashTable.HashTableImplementation(hashDataArray.length);
        HashTableNew tableNew = new HashTableNew(hashDataArray.length);
        long hashTime = System.currentTimeMillis();
        for(String s : hashDataArray) {
//            String[] sc = s.split("\\s+",2);
//            System.out.println(sc[0]);
            tableNew.put(s, s); //the mistake being made was that vak
            }
//        tableImplementation.dump();
        long createTime = System.currentTimeMillis() - hashTime;
        long startHash = System.currentTimeMillis();
        int countHash = 0;
        System.out.println("our data array length is " + findDataArray.length);
        for(String s : findDataArray) {
            String match = tableNew.get(s);
            if (match != null){
                System.out.println("matched item is " + match);
//                if (match.equals(s)) {
                    countHash++;
//                }
            }
        }
        long endHash = System.currentTimeMillis() - startHash;
        System.out.printf("Found %d / 500 entries. Time taken: %s\n", countHash, timeConverter(createTime + endHash));
        System.out.print("Creating time: " + timeConverter(createTime) + "\n");
        System.out.print("Searching time: " + timeConverter(endHash) + "\n");

    }
// -------------------------------------------------------------------------------------------------

    private static String timeConverter(long time) {
        return String.format("%d min. %d sec. %d ms.",
                ((time - time % 1000) / 1000) - ((time - time % 1000) / 1000) % 60,
                ((time - time % 1000) / 1000) % 60,
                time % 1000);
    }






}