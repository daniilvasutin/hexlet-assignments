package exercise.controller;

import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

// BEGIN
@RestController
public class WelcomeController {

    @GetMapping(path = "/welcome")
    public String showWelcome() {
        int hour = LocalDateTime.now().getHour();

        if (hour >= 6 && hour < 22) {
            return new Day().getName();
        }

        return new Night().getName();
    }
}
// END
