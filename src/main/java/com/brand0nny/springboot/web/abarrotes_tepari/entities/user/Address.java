package com.brand0nny.springboot.web.abarrotes_tepari.entities.user;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String street;
    @Column
    @NotNull
    private int number;
    @Column
    @NotNull
    private int postalCode;
    @Column
    @NotBlank
    private String city;
    @Column
    @NotBlank
    private String state;
    @Column
    @NotBlank
    private String country;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_address", 
               joinColumns = @JoinColumn(name = "address_id"),
               inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> user;
}
