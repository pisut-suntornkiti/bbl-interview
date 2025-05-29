package com.bbl.pisut.interview.demo.dao;

import com.bbl.pisut.interview.demo.dto.UserDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public class UserDao {
  private final String DATA_FILE_PATH = "data.json";
  private static List<UserDto> users;

  UserDao() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    InputStream inputStream = new ClassPathResource(DATA_FILE_PATH).getInputStream();
    users = objectMapper.readValue(inputStream, new TypeReference<List<UserDto>>() {});
  }

  public List<UserDto> getAllUser() {
    return users;
  }

  public UserDto getUserById(String userId) {
    return users.stream().filter(itr -> itr.getId().equalsIgnoreCase(userId))
            .findFirst()
            .orElse(null);
  }

  public boolean addUser(UserDto user) {
    users.add(user);
    return true;
  }

  public boolean updateUser(UserDto user) {
    UserDto oldUser = getUserById(user.getId());
    int index = users.indexOf(oldUser);
    users.set(index, user);
    return true;
  }

  public boolean deleteUser(String userId) {
    UserDto oldUser = getUserById(userId);
    users.remove(oldUser);
    return true;
  }
}
