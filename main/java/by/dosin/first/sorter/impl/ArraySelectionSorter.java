package by.dosin.first.sorter.impl;

import by.dosin.first.entity.IntArray;
import by.dosin.first.factory.IntArrayFactory;
import by.dosin.first.sorter.Sorter;

public class ArraySelectionSorter implements Sorter {

    @Override
    public IntArray sortArray(IntArray unsortedArray) {

        int[] sortedArray = unsortedArray.getArray().clone();
        int lenght = sortedArray.length;

        for(int i = 0; i < lenght - 1; i++) {
            int minIndex = i;

            for(int j = i + 1; j < lenght - 1; j++) {

                if(sortedArray[j] < sortedArray[minIndex])
                {
                    minIndex = j;
                }
            }

            if(minIndex != i)
            {
                int temp = sortedArray[i];
                sortedArray[i] = sortedArray[minIndex];
                sortedArray[minIndex] = temp;
            }
        }

        return IntArrayFactory.createFromArray(sortedArray);
    }
}
