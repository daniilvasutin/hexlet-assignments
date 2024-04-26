package exercise;

import java.util.Arrays;

// BEGIN
class MinThread extends Thread {
    private int min;
    private int[] numbs;

    MinThread(int[] numbers) {
        numbs = numbers;
    }

    @Override
    public void run() {
        System.out.println("Thread min start");
        min = Arrays.stream(numbs).min().getAsInt();
        System.out.println("Thread min stop");
    }

    public int getMin() {
        return min;
    }
}
// END
