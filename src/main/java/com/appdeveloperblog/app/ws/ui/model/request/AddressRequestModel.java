package com.appdeveloperblog.app.ws.ui.model.request;

public class AddressRequestModel {
  private final String city;

  private final String country;

  private final String streetName;

  private final String postalCode;

  private final String type;

  public AddressRequestModel(String city, String country, String streetName, String postalCode, String type) {
    this.city = city;
    this.country = country;
    this.streetName = streetName;
    this.postalCode = postalCode;
    this.type = type;
  }

  public String getCity() {
    return this.city;
  }

  public String getCountry() {
    return this.country;
  }

  public String getStreetName() {
    return this.streetName;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public String getType() {
    return this.type;
  }
}
