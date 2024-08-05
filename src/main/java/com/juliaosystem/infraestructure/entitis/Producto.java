package com.juliaosystem.infraestructure.entitis;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

        @Id
        private UUID id;

        @Column(nullable = false)
        private String nombre;

        @Column(nullable = false)
        private BigDecimal precio;

        @Column(nullable = false)
        private Integer cantidad;

        @Column(nullable = false)
        private Long idBussines;

        @Column(nullable = false, name = "id_categoria")
        private UUID categoria;


        @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
        @ToString.Exclude
        private List<ProductImage> imagenes;

}