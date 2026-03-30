package org.pruebatecnica.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "phones_users")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;
}
