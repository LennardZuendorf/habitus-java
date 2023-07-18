package tech.ignitr.habitus.web.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.ignitr.habitus.data.habits.Habit;
import tech.ignitr.habitus.data.users.User;
import tech.ignitr.habitus.service.auth.AuthenticationResponse;
import tech.ignitr.habitus.service.habits.HabitService;
import tech.ignitr.habitus.service.users.UserService;
import tech.ignitr.habitus.web.auth.AuthModel;

import java.util.List;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
public class UserController {

    private final String baseURL = "/api/users";
    private final UserService userService;
    private final HabitService service;

    @GetMapping(path = baseURL+"/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id){
        return userService.getUser(id);
    }

    @PutMapping(path=baseURL)
    public ResponseEntity<User> putUser(@RequestBody UserModel request){
        return userService.putUser(request);
    }

    @DeleteMapping(path=baseURL+"/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id){
        return userService.deleteUser(id);
    }

    /**
     * API call for deleting all habits (HabitEntity)
     * @param id - id of the user all habits should be deleted from
     * @return ResponseEntity containing the status code from service method
     */
    @DeleteMapping(baseURL+"/{id}/habits")
    public ResponseEntity <Void> deleteAllHabits (@PathVariable("id") UUID id){
        return service.deleteAllHabits(id);
    }

    /**
     * API call for getting all habits (HabitEntity) by userID
     * @param id - the user ID to be selected by
     * @return ResponseEntity containing the status code from service method and a list of Habits
     */
    @GetMapping(baseURL+"/{id}/habits")
    public ResponseEntity <List<Habit>> getAllHabit(@PathVariable("id") UUID id){
        return service.getHabits(id);
    }

    /**
     * API call for creating a new habit (HabitEntity)
     * @param model - all of HabitEntity params
     * @return ResponseEntity containing the status code from service method and the created Habit
     */
    @PutMapping(path=baseURL+"/{id}/credentials")
    public ResponseEntity<AuthenticationResponse> updateUserCredentials(@RequestBody AuthModel model, @PathVariable UUID id){
        return userService.updateUserCredentials(model, id);
    }
}
