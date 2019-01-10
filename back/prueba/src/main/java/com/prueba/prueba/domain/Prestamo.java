package com.prueba.prueba.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="pru_prestamo")
@Getter
@Setter
@ToString
public class Prestamo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "monto")
    private BigDecimal monto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="idCliente", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente cliente;
}
