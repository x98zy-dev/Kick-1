package by.dosin.first.repository.comparator;

import by.dosin.first.entity.IntArray;
import java.util.Comparator;

public class FirstElementComparator implements Comparator<IntArray> {
    @Override
    public int compare(IntArray array1, IntArray array2) {
        int[] arr1 = array1.getArray();
        int[] arr2 = array2.getArray();

        if (arr1.length == 0 && arr2.length == 0) return 0;
        if (arr1.length == 0) return -1;
        if (arr2.length == 0) return 1;

        return Integer.compare(arr1[0], arr2[0]);
    }
}