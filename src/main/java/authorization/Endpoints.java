package authorization;

import lombok.Getter;

public class Endpoints {
    @Getter private static final String USERS = "/users";
    @Getter private static final String USER_BY_ID = "/users/{userId}";
}
