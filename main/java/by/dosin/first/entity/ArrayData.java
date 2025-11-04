package by.dosin.first.entity;

public class ArrayData {
    private final double average;
    private final int sum;
    private final int min;
    private final int max;

    public ArrayData(double average, int sum, int min, int max) {
        this.average = average;
        this.sum = sum;
        this.min = min;
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public int getSum() {
        return sum;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ArrayData{");
        sb.append("average=").append(average);
        sb.append(", sum=").append(sum);
        sb.append(", min=").append(min);
        sb.append(", max=").append(max);
        sb.append('}');
        return sb.toString();
    }
}