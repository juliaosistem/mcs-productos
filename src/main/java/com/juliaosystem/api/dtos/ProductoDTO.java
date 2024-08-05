package com.juliaosystem.api.dtos;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDTO {


    @JsonProperty("id")
    private UUID id;


    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("")
    private BigDecimal precio;

    @JsonProperty("")
    private Integer cantidad;

    @JsonProperty("idBussines")
    private Long idBussines;

    @JsonProperty("categoria")
    private UUID categoria;


    @JsonProperty("imagenes")
    private List<ProductosImagenesDTO> imagenes;




}
