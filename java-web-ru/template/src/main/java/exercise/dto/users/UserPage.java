package exercise.dto.users;

import exercise.model.User;

import lombok.Getter;

import java.util.Optional;

// BEGIN
//@AllArgsConstructor
@Getter
public class UserPage {
    private User user;

    public UserPage(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
// END
