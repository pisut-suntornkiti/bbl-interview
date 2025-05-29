package com.bbl.pisut.interview.demo.servcie;

import com.bbl.pisut.interview.demo.dao.UserDao;
import com.bbl.pisut.interview.demo.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserDao userDao;

  public List<UserDto> getAllUser() {
    return userDao.getAllUser();
  }

  public UserDto getUserById(String userId) {
    return userDao.getUserById(userId);
  }

  public boolean addUser(UserDto user) {
    return userDao.addUser(user);
  }

  public boolean updateUser(UserDto user) {
    return userDao.updateUser(user);
  }

  public boolean deleteUser(String userId) {
    return userDao.deleteUser(userId);
  }

  public boolean validateUser(UserDto user) {
    if (user.getId() == null || user.getId().isEmpty()) {
      return false;
    }
    if (user.getName() == null || user.getName().isEmpty()) {
      return false;
    }
    if (user.getUsername() == null || user.getUsername().isEmpty()) {
      return false;
    }
    return true;
  }

  public boolean isExistUser(String userId) {
    return userDao.getUserById(userId) != null;
  }
}
