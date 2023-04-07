package ru.practicum.shareit.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.UserClient;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.utils.Create;
import ru.practicum.shareit.utils.Update;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserClient userClient;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return userClient.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@Validated({Create.class}) @RequestBody UserDto user) {
        return userClient.createUser(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
       return userClient.deleteUser(userId);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@Validated({Update.class}) @RequestBody UserDto user, @PathVariable Long userId) {
        return userClient.updateUser(user, userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getById(@PathVariable Long userId) {
        return userClient.findById(userId);
    }
}