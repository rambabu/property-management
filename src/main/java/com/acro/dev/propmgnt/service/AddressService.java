package com.acro.dev.propmgnt.service;

import com.acro.dev.propmgnt.request.AddressRequest;
import com.acro.dev.propmgnt.response.AddressResponse;
import org.springframework.transaction.annotation.Transactional;

public interface AddressService {
     AddressResponse createAddress(AddressRequest addressRequest);
     @Transactional
     AddressResponse updateAddress(Long id, AddressRequest addressRequest);
     AddressResponse getById(Long id);
     Boolean deleteAddressById(Long id);
}
