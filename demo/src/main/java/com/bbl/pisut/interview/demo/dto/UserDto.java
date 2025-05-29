package com.bbl.pisut.interview.demo.dto;

import lombok.Data;

@Data
public class UserDto {
  private String id;
  private String name;
  private String username;
  private String email;
  private String phone;
  private String website;
  private Address address;
  private Company company;

  @Data
  private static class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Data
    private static class Geo {
      private String lat;
      private String lng;
    }
  }

  @Data
  private static class Company {
    private String name;
    private String catchPhrase;
    private String bs;
  }

}
