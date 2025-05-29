package com.bbl.pisut.interview.demo.controller;

import com.bbl.pisut.interview.demo.dto.UserDto;
import com.bbl.pisut.interview.demo.servcie.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  /**
   * -	GET /users: Retrieve a list of all users.
   * -	GET /users/{userId}: Retrieve details of a specific user.
   * -	POST /users: Create a new user.
   * -	PUT /users/{userId}: Update details of a specific user.
   * -	DELETE /users/{userId}: Delete a specific user.
   */

  @GetMapping("")
  public @ResponseBody ResponseEntity<?> getAllUser() {
    try {
      List<UserDto> users = userService.getAllUser();
      return ResponseEntity.ok(users);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @GetMapping("{userId}")
  public @ResponseBody ResponseEntity<?> getUserById(@PathVariable String userId) {
    try {
      UserDto users = userService.getUserById(userId);
      if (users == null) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(users);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @PostMapping("")
  public @ResponseBody ResponseEntity<?> addUser(@RequestBody UserDto user) {
    try {
      if (userService.validateUser(user) && !userService.isExistUser(user.getId())) {
        boolean isSuccess = userService.addUser(user);
        if (isSuccess) {
          return ResponseEntity.ok().build();
        } else {
          return ResponseEntity.internalServerError().build();
        }
      } else {
        return ResponseEntity.badRequest().build();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @PutMapping("{userId}")
  public @ResponseBody ResponseEntity<?> updateUser(@PathVariable String userId,
                                                    @RequestBody UserDto user) {
    try {
      if (userService.validateUser(user) && userService.isExistUser(userId)) {
        boolean isSuccess = userService.updateUser(user);
        if (isSuccess) {
          return ResponseEntity.ok().build();
        } else {
          return ResponseEntity.internalServerError().build();
        }
      } else {
        return ResponseEntity.badRequest().build();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @DeleteMapping("{userId}")
  public @ResponseBody ResponseEntity<?> deleteUser(@PathVariable String userId) {
    try {
      if (userService.isExistUser(userId)) {
        boolean isSuccess = userService.deleteUser(userId);
        if (isSuccess) {
          return ResponseEntity.ok().build();
        } else {
          return ResponseEntity.internalServerError().build();
        }
      } else {
        return ResponseEntity.badRequest().build();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

}
