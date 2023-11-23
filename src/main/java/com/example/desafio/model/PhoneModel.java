package com.example.desafio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PHONES")
public class PhoneModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "number", nullable = false, length = 255)
    private String number;

    @Column(name = "city_code", nullable = false, length = 255)
    private String cityCode;

    @Column(name = "country_code", nullable = false, length = 255)
    private String countryCode;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private UserModel user;

}
