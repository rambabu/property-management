package com.acro.dev.propmgnt.request;
import com.acro.dev.propmgnt.entity.AddressType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private Long addressId;
    private Long ownerId;

    @NotEmpty
    private String lineOne;
    private String lineTwo;
    //@NotEmpty
    private String city;
    @NotEmpty
    private String state;
   // @NotNull
    private int zipcode;
    @NotNull(message = "type of address is necessary")
    private AddressType type;

}
