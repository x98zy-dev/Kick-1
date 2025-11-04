package by.dosin.first.repository.comparator;

import by.dosin.first.entity.IntArray;
import java.util.Comparator;

public class IdComparator implements Comparator<IntArray> {
    @Override
    public int compare(IntArray array1, IntArray array2) {
        return array1.getId().compareTo(array2.getId());
    }
}