package com.juliaosystem.infraestructure.adapters.primary;


import com.common.lib.api.response.PlantillaResponse;
import com.common.lib.utils.UserResponses;
import com.common.lib.utils.enums.ResponseType;
import com.juliaosystem.api.dtos.ProductoDTO;
import com.juliaosystem.infraestructure.adapters.secundary.DefaultAdapter;
import com.juliaosystem.infraestructure.entitis.Producto;

import com.juliaosystem.infraestructure.services.secundary.ProductoServiceImpl;
import org.jetbrains.annotations.NotNull;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductoImpl extends DefaultImpl<ProductoDTO, ProductoDTO, Producto, UUID> {

    private final ProductoServiceImpl productoService;
    private final UserResponses<ProductoDTO> userResponses;

    public ProductoImpl(@NotNull DefaultAdapter<ProductoDTO, ProductoDTO, Producto, UUID> crudSecondaryService, ProductoServiceImpl productoService , UserResponses<ProductoDTO> userResponses) {
        super(crudSecondaryService);
        this.productoService  = productoService;
        this.userResponses =  userResponses;
    }

    @Override
    public PlantillaResponse<ProductoDTO> update(ProductoDTO request) {
        var productoDTO =  productoService.getfindByIdForUpdate(request.getId());
        if (productoDTO.getCantidad() == null) {
            return userResponses.buildResponse(ResponseType.NO_ENCONTRADO.getCode(),request);
        }
            if(productoDTO.getCantidad().equals(request.getCantidad())){
                return super.add(request,request.getId());
            }else {
               return productoService.updateCantidad(request);
            }
    }
}

