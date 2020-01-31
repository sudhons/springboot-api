package com.appdeveloperblog.app.ws.ui.model.request;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDetailsRequestModel {
  @NotBlank(message = "firstName is required")
  private String firstName;

  @NotBlank(message = "lastName is required")
  private String lastName;

  @NotBlank(message = "email is required")
  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "password is required")
  private String password;

  private List<AddressRequestModel> addresses;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<AddressRequestModel> getAddresses() {
    return addresses;
  }

  public void setAdresses(List<AddressRequestModel> addresses) {
    this.addresses = addresses;
  }
}
