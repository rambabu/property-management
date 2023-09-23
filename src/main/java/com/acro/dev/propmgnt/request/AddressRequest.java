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
   // @NotNull
    private Long addressId;

    @NotEmpty
    private String lineOne;

    private String lineTwo;
    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotNull
    private int zipcode;
    @NotEmpty
    private AddressType type;

}
