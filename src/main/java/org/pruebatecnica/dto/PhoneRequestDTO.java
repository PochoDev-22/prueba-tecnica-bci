package org.pruebatecnica.dto;

import lombok.Data;

@Data
public class PhoneRequestDTO {
    private String number;
    private String cityCode;
    private String countryCode;
}
