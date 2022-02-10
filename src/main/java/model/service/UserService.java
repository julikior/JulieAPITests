package model.service;

import model.User;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    Map<String, User> userMap;

    public UserService() {
        userMap = new HashMap<>();
    }

    public User saveUser(User user) {
        userMap.put(String.valueOf(user.getId()), user);
        return userMap.get(user.getId());
    }

    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }

    public Optional<User> getUser(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public void deleteUser(String id) {
        userMap.remove(id);
    }
}
