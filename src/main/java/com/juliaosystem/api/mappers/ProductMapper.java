package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.ProductoDTO;
import com.juliaosystem.infraestructure.entitis.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel ="spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductMapper {

    @Mapping(target = "imagenes", source = "imagenes")
    ProductoDTO productToProductDTO(Producto producto);

    @Mapping(target = "imagenes", source = "imagenes")
    Producto productDTOToProduct(ProductoDTO productoDTO);
}
