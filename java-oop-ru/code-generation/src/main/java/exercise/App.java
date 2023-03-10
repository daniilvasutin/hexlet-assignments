package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static void save(Path pathToFile, Car car) throws IOException {
        String jsonCar = car.serialize();
        Files.writeString(pathToFile, jsonCar, StandardOpenOption.WRITE);
    }

    public static Car extract(Path pathToFile) throws IOException {
        String jsonCar = Files.readString(pathToFile);
        Car car = Car.unserialize(jsonCar);
        return car;
    }
}
// END
