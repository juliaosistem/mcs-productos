package com.juliaosystem.api.mappers;

import com.juliaosystem.api.dtos.ProductosImagenesDTO;
import com.juliaosystem.infraestructure.entitis.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel ="spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductImageMapper {


    @Mapping(target = "productoId", source = "producto.id")
    ProductosImagenesDTO productImageToProductImageDTO(ProductImage productImage);

    @Mapping(target = "producto", ignore = true)
    ProductImage productImageDTOToProductImage(ProductosImagenesDTO productosImagenesDTO);
}
