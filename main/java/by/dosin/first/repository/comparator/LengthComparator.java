package by.dosin.first.repository.comparator;

import by.dosin.first.entity.IntArray;
import java.util.Comparator;

public class LengthComparator implements Comparator<IntArray> {
    @Override
    public int compare(IntArray array1, IntArray array2) {
        return Integer.compare(array1.length(), array2.length());
    }
}