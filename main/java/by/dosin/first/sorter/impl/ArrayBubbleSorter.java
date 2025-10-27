package by.dosin.first.sorter.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.sorter.Sorter;

public class ArrayBubbleSorter implements Sorter {

    @Override
    public IntArray sortArray(IntArray unsortedArray) {

        int[] sortedArray = unsortedArray.getArray().clone();
        int length = sortedArray.length;

        boolean swap = true;
        int temp;

        while (swap) {

        swap = false;
            for(int i = 0; i < length - 1; i++) {
                if (sortedArray[i] > sortedArray[i + 1]) {
                    swap = true;
                    temp = sortedArray[i];
                    sortedArray[i] = sortedArray[i + 1];
                    sortedArray[i + 1] = temp;
                }
            }
            length--;
        }

        return IntArrayFactory.createFromArray(sortedArray);
    }
}
