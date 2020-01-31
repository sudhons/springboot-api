package com.appdeveloperblog.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "addresses")
public class AddressEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false, unique = true, length = 30)
  private String addressId;

  @Column(nullable = false, length = 15)
  private String city;

  @Column(nullable = false, length = 15)
  private String country;

  @Column(nullable = false, length = 100)
  private String streetName;

  @Column(nullable = false, length = 7)
  private String postalCode;

  @Column(nullable = false, length = 10)
  private String type;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity userDetails;

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAddressId() {
    return this.addressId;
  }

  public void setAddressId(String addressId) {
    this.addressId = addressId;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return this.country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getStreetName() {
    return this.streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public UserEntity getUserDetails() {
    return this.userDetails;
  }

  public void setUserDetails(UserEntity userDetails) {
    this.userDetails = userDetails;
  }

}
