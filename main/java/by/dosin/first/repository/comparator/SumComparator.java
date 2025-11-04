package by.dosin.first.repository.comparator;

import by.dosin.first.entity.IntArray;
import java.util.Comparator;

public class SumComparator implements Comparator<IntArray> {
    @Override
    public int compare(IntArray array1, IntArray array2) {
        int sum1 = calculateSum(array1);
        int sum2 = calculateSum(array2);
        return Integer.compare(sum1, sum2);
    }

    private int calculateSum(IntArray array) {
        int sum = 0;
        for (int value : array.getArray()) {
            sum += value;
        }
        return sum;
    }
}