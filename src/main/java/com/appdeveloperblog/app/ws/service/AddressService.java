package com.appdeveloperblog.app.ws.service;

import java.util.List;

import com.appdeveloperblog.app.ws.shared.dto.AddressDto;

public interface AddressService {
  AddressDto getAddress(String addressId);

  List<AddressDto> getAddresses(String userId);
}
