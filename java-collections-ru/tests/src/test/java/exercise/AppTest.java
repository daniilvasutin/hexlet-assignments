package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    List<Integer> numbers1;
    List<Integer> numbers2;


    @BeforeEach
    void initNumberList() {
         this.numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
         this.numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
    }

    @Test
    void testTake() {
        // BEGIN
        assertThat(App.take(numbers1,2)).hasSize(2);
        assertThat(App.take(numbers2,8)).hasSize(numbers2.size());
        // END
    }
}
