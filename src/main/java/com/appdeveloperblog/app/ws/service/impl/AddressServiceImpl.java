package com.appdeveloperblog.app.ws.service.impl;

import java.lang.reflect.Type;
import java.util.List;

import com.appdeveloperblog.app.ws.exceptions.AddressServiceException;
import com.appdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appdeveloperblog.app.ws.io.entity.UserEntity;
import com.appdeveloperblog.app.ws.io.repositories.AddressRepository;
import com.appdeveloperblog.app.ws.io.repositories.UserRepository;
import com.appdeveloperblog.app.ws.service.AddressService;
import com.appdeveloperblog.app.ws.shared.dto.AddressDto;
import com.appdeveloperblog.app.ws.ui.model.response.ErrorMessages;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public AddressDto getAddress(String addressId) {
    AddressEntity addressEntity = addressRepository.findByAddressId(addressId);

    if (addressEntity == null)
      throw new AddressServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

    AddressDto returnValue = new AddressDto();
    BeanUtils.copyProperties(addressEntity, returnValue);

    return returnValue;
  }

  @Override
  public List<AddressDto> getAddresses(String userId) {
    UserEntity userEntity = userRepository.findByUserId(userId);

    if (userEntity == null)
      throw new AddressServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

    Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);

    Type listType = new TypeToken<List<AddressDto>>() {
    }.getType();

    List<AddressDto> returnValue = new ModelMapper().map(addresses, listType);

    return returnValue;
  }
}
