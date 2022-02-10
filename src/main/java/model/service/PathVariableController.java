package model.service;

import lombok.Data;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.of;

@RestController
@Data
@RequestMapping("/users")
public class PathVariableController {
    UserService userService;

    public PathVariableController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public User createUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser (@PathVariable String id) {
        Optional<User> user = userService.getUser(id);
        return (user.isPresent() ? of(user) : notFound().build());
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
