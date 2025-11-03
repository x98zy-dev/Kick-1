package by.dosin.first.sorter.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.sorter.Sorter;

public class ArrayQuickSorter implements Sorter {

    @Override
    public IntArray sortArray(IntArray unsortedArray) {
        int[] sortedArray = unsortedArray.getArray().clone();
        quickSort(sortedArray, 0, sortedArray.length - 1);
        return IntArrayFactory.createFromArray(sortedArray);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}