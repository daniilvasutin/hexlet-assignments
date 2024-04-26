package exercise;

import java.util.Arrays;

// BEGIN
class MaxThread extends Thread {

    private int max;
    private int[] numbs;

    MaxThread(int[] numbers) {
        numbs = numbers;
    }

    @Override
    public void run() {
        System.out.println("Thread max start");
        max = Arrays.stream(numbs).max().getAsInt();
        System.out.println("Thread max stop");
    }

    public int getMax() {
        return max;
    }
}
// END
