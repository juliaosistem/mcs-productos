package com.juliaosystem.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductosImagenesDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("productoId")
    private UUID productoId;
}
