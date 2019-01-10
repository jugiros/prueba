package com.prueba.prueba.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="pru_cliente")
@Getter
@Setter
@ToString
public class Cliente {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "nombre")
    private String nombre;
}
