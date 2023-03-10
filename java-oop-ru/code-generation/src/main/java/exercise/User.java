package exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// BEGIN
@Getter
@AllArgsConstructor
@NoArgsConstructor
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
