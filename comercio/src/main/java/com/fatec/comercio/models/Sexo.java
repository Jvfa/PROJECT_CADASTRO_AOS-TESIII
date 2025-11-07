package com.fatec.comercio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Getter
@Setter
@NoArgsConstructor
public class Sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer codsexo;

    @Column
    private String nomesexo;
}