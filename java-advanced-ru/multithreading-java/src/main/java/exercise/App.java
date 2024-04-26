package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN

    public static Map<String, Integer> getMinMax(int[] numbers) {

        MaxThread maxThread = new MaxThread(numbers);
        MinThread minThread = new MinThread(numbers);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
        }

        Map<String, Integer> minMaxMap = Map.of("min", minThread.getMin(), "max", maxThread.getMax());

        return minMaxMap;
    }
    // END
}
