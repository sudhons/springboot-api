package com.appdeveloperblog.app.ws.ui.controller;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

import javax.validation.Valid;

import com.appdeveloperblog.app.ws.service.AddressService;
import com.appdeveloperblog.app.ws.service.UserService;
import com.appdeveloperblog.app.ws.shared.dto.AddressDto;
import com.appdeveloperblog.app.ws.shared.dto.UserDto;
import com.appdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appdeveloperblog.app.ws.ui.model.response.AddressRest;
import com.appdeveloperblog.app.ws.ui.model.response.OperationStatusModel;
import com.appdeveloperblog.app.ws.ui.model.response.OperationSuccessModel;
import com.appdeveloperblog.app.ws.ui.model.response.UserRest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "users", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Validated
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private AddressService addressService;

  @GetMapping(path = "/{userId}")
  public OperationStatusModel<UserRest> getUser(
      @PathVariable @Length(min = 30, max = 30, message = "Invalid user ID") String userId) {

    UserDto userDto = userService.getUserByUserId(userId);
    ModelMapper modelMapper = new ModelMapper();

    UserRest returnValue = modelMapper.map(userDto, UserRest.class);

    return new OperationSuccessModel<>(returnValue);
  }

  @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  @ResponseStatus(HttpStatus.CREATED)
  public OperationStatusModel<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

    ModelMapper modelMapper = new ModelMapper();

    UserDto userDto = modelMapper.map(userDetails, UserDto.class);

    UserDto createdUser = userService.createUser(userDto);

    UserRest returnValue = modelMapper.map(createdUser, UserRest.class);

    return new OperationSuccessModel<>(returnValue);
  }

  @PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  public OperationStatusModel<UserRest> updateUser(
      @PathVariable @Length(min = 30, max = 30, message = "Invalid user ID") String userId,
      @RequestBody UserDetailsRequestModel userDetails) {

    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userDetails, userDto);

    UserDto updatedUser = userService.updateUser(userId, userDto);

    UserRest returnValue = new ModelMapper().map(updatedUser, UserRest.class);

    return new OperationSuccessModel<>(returnValue);
  }

  @DeleteMapping(path = "/{userId}")
  public OperationStatusModel<UserRest> deleteUser(
      @PathVariable @Length(min = 30, max = 30, message = "Invalid user ID") String userId) {
    userService.deleteUser(userId);

    return new OperationSuccessModel<>();
  }

  @GetMapping()
  public OperationSuccessModel<List<UserRest>> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "25") int limit) {

    List<UserDto> users = userService.getUsers(page, limit);

    Type listType = new TypeToken<List<UserRest>>() {
    }.getType();
    List<UserRest> returnValue = new ModelMapper().map(users, listType);

    return new OperationSuccessModel<>(returnValue);
  }

  @GetMapping("/{userId}/addresses")
  public Resource<OperationSuccessModel<List<AddressRest>>> getUserAddresses(
      @PathVariable @Length(min = 30, max = 30, message = "Invalid user ID") String userId) {

    List<AddressDto> addressDto = addressService.getAddresses(userId);

    Type listType = new TypeToken<List<AddressRest>>() {
    }.getType();
    List<AddressRest> returnValue = new ModelMapper().map(addressDto, listType);

    returnValue.forEach(address -> {
      address.add(linkTo(methodOn(UserController.class).getAddress(userId, address.getAddressId())).withSelfRel());
      address.add(linkTo(methodOn(UserController.class).getUser(userId)).withRel("user"));
    });

    return new Resource<>(new OperationSuccessModel<>(returnValue));
  }

  @GetMapping("/{userId}/addresses/{addressId}")
  public Resource<OperationSuccessModel<AddressRest>> getAddress(
      @PathVariable @Length(min = 30, max = 30, message = "Invalid user ID") String userId,
      @PathVariable @Length(min = 30, max = 30, message = "Invalid address ID") String addressId) {

    AddressDto addressDto = addressService.getAddress(addressId);

    AddressRest returnValue = new ModelMapper().map(addressDto, AddressRest.class);

    Link addressLink = linkTo(methodOn(UserController.class).getAddress(userId, addressId)).withSelfRel();
    Link addressesLink = linkTo(methodOn(UserController.class).getUserAddresses(userId)).withRel("addresses");
    Link userLink = linkTo(methodOn(UserController.class).getUser(userId)).withRel("user");

    returnValue.add(addressLink);
    returnValue.add(addressesLink);
    returnValue.add(userLink);

    return new Resource<>(new OperationSuccessModel<>(returnValue));
  }
}
