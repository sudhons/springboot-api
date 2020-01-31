package com.appdeveloperblog.app.ws.io.repositories;

import com.appdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appdeveloperblog.app.ws.io.entity.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
  AddressEntity findByAddressId(String addressId);

  Iterable<AddressEntity> findAllByUserDetails(UserEntity user);
}
