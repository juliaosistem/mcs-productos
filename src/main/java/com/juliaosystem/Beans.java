package com.juliaosystem;

import com.common.lib.api.mappers.GenericMapper;
import com.common.lib.utils.UserResponses;
import com.common.lib.utils.errors.AbtractError;
import com.juliaosystem.api.dtos.ProductoDTO;
import com.juliaosystem.api.mappers.ProductMapper;
import com.juliaosystem.infraestructure.adapters.secundary.ProductoAdapter;
import com.juliaosystem.infraestructure.entitis.Producto;
import com.juliaosystem.infraestructure.repository.DefualtRepository;
import com.juliaosystem.infraestructure.repository.ProductoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class Beans {

    @Bean
    public ProductoAdapter productoAdapter(GenericMapper<ProductoDTO, ProductoDTO, Producto> mapper,
                                           AbtractError abstractError,
                                           UserResponses<ProductoDTO> userResponses,
                                           DefualtRepository<Producto, UUID> defaultRepository,
                                           ProductoRepository productoRepository,
                                           ProductMapper productMapper

    ) {
        return new ProductoAdapter(mapper, abstractError, userResponses, ProductoDTO.builder().build(), Producto.builder().build(), defaultRepository ,productoRepository, productMapper);
    }
}
